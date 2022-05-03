package com.group.exam.qna.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.group.exam.admin.command.AdminAuthInfoCommand;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.qna.command.RegistCommand;
import com.group.exam.qna.service.QnaService;
import com.group.exam.qna.vo.QnaVo;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
@RequestMapping("/qna")
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);

	private QnaService qnaService;
	
	@Autowired
	public QnaController(QnaService qnaService) {
		this.qnaService = qnaService;
	}
	
	
	//글 목록
	@RequestMapping("/list")
	public String list(Model model, Criteria cri) {
		List<QnaVo> list = qnaService.list(cri);
		PagingVo pageMaker = new PagingVo();
		
		pageMaker.setCri(cri);
		int boardTotal = qnaService.qnaTotalList();
		pageMaker.setTotalCount(boardTotal);
		
		model.addAttribute("boardTotal", boardTotal);
		model.addAttribute("pageMaker", pageMaker);

		model.addAttribute("qnaList", list);
		return "/qna/list";
	}
	
	//신규 글 생성
	@RequestMapping(value="/write", method= RequestMethod.GET)
	public String insert(HttpSession session) {
		if(session.getAttribute("adminAuthInfoCommand")==null) {
			return "/main";
		}
		return "/qna/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String insert(@ModelAttribute RegistCommand registCommand, HttpSession session, HttpServletRequest request) {
		AdminAuthInfoCommand adminSession = (AdminAuthInfoCommand) session.getAttribute("adminAuthInfoCommand");
		LoginCommand memberSession = (LoginCommand) session.getAttribute("memberLogin");
		QnaVo vo = new QnaVo();

		System.out.println(registCommand);
		if(adminSession != null) {
			vo.setQnaWriter(adminSession.getAdminNickname());

		}else if(memberSession != null){
		vo.setMemberSeq(memberSession.getMemberSeq());
		}
		
		MultipartFile uploadfile = registCommand.getUploadfile();
		if(!uploadfile.isEmpty()) {
			vo.setQnaFilePath(qnaService.upload("qna", uploadfile, session));
			vo.setQnaFileName(uploadfile.getOriginalFilename());
		}
		
		vo.setQnaTitle(registCommand.getQnaTitle());
		vo.setQnaContent(registCommand.getQnaContent());

		qnaService.write(vo);
		
		return "redirect:list ";	
	}

	//글 상세 화면
	@RequestMapping("/detail/{qnaSeq}")
	public String detail(@PathVariable("qnaSeq") Long qnaSeq, Model model,HttpSession session) {
		//조회수 증가
		qnaService.qnaRead(qnaSeq);
		QnaVo vo = qnaService.detail(qnaSeq);
		model.addAttribute("vo", vo);
		
		
		//수정 삭제 글 보이게..
		AdminAuthInfoCommand adminSession = (AdminAuthInfoCommand) session.getAttribute("adminAuthInfoCommand");
		LoginCommand memberSession = (LoginCommand) session.getAttribute("memberLogin");
		
		if(adminSession != null) {
	
		}
		if(adminSession != null) {
			String admin = adminSession.getAdminNickname();
			model.addAttribute("admin", admin);
		}
		
		else if( memberSession != null) {
			if(memberSession.getMemberNickname().equals(vo.getQnaWriter())) {
				//본인이 쓴 글일 때
				String member = memberSession.getMemberNickname();
				model.addAttribute("member", member);
			}
		}
		
		return "/qna/detail";
	}
	
	//글 삭제 처리
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam Long qnaSeq, HttpSession session,Model model) {
		QnaVo vo = qnaService.detail(qnaSeq);
		
		//파일 삭제
		if(vo.getQnaFilePath() != null) {
			File file = new File("C:\\project\\workspace\\resources\\"+vo.getQnaFilePath());
			if( file.exists() ) {
				file.delete();
			}
		}
		qnaService.qnaDelete(qnaSeq);
		
		return"redirect:list";
	}
	//글 수정
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(@RequestParam Long qnaSeq, Model model) {
		model.addAttribute("vo", qnaService.detail(qnaSeq));
		return "qna/modify";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@ModelAttribute QnaVo vo, HttpSession session) {
		QnaVo qna = qnaService.detail(vo.getQnaSeq());
		String uuid= session.getServletContext().getRealPath("resources") + qna.getQnaFilePath();
		MultipartFile file = vo.getUploadfile();
		if(!file.isEmpty()) {
			vo.setQnaFileName(file.getOriginalFilename());
//			vo.setQnaFilePath();
			if(qna.getQnaFileName() != null) {
				File f = new File(uuid);
				if(f.exists()) {
					f.delete();
				}
			}else {
			
			}
		}
		
		qnaService.qnaUpdate(vo);
		return "redirect:detail/"+vo.getQnaSeq();
	}

	//첨부 파일
	@ResponseBody @RequestMapping("/download")
	public void download(Long qnaSeq, HttpSession session, HttpServletResponse response) {
		QnaVo vo = qnaService.detail(qnaSeq);
		qnaService.download(vo.getQnaFileName(),vo.getQnaFilePath(), session, response);
	}
	

	//답글쓰기
	@RequestMapping(value="/reply", method=RequestMethod.GET)
	public String reply(@RequestParam Long qnaSeq, Model model,HttpSession session) {
		if(session.getAttribute("adminAuthInfoCommand") == null) {
			return "/main";
		}
		QnaVo vo = qnaService.detail(qnaSeq);
		model.addAttribute("vo", vo);
		return "/qna/reply";
	}
	
	@RequestMapping(value="/reply", method=RequestMethod.POST)
	public String reply(@ModelAttribute QnaVo reply, HttpSession session, HttpServletRequest request) {
		logger.info("");
		
		
		AdminAuthInfoCommand admin = (AdminAuthInfoCommand) session.getAttribute("adminAuthInfoCommand");
		//session.getAttribute("AdminAuthInfoCommand");
		System.out.println(admin);
		QnaVo vo = new QnaVo();
		vo.setQnaWriter(admin.getAdminNickname());

		System.out.println(reply);
		
		MultipartFile uploadfile = reply.getUploadfile();
		if(!uploadfile.isEmpty()) {
			
			vo.setQnaFilePath(qnaService.upload("qna", uploadfile, session));
			vo.setQnaFileName(uploadfile.getOriginalFilename());
		}
		
		vo.setQnaRoot(reply.getQnaRoot());
		vo.setQnaStep(reply.getQnaStep());
		vo.setQnaIndent(reply.getQnaIndent());
		vo.setQnaTitle(reply.getQnaTitle());
		vo.setQnaContent(reply.getQnaContent());
		
		System.out.println(vo);
		qnaService.qnaReplyInsert(vo);
		return "redirect:list";
	}
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public void fileupload(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam MultipartFile upload)
			throws Exception {
		OutputStream out = null;
		PrintWriter printWriter = null;

		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 서버의 업로드할 물리적 위치
		String resources = "C:/qCali/resources/upload";
		String folder =  resources + "/" + "qna" + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		// 파일 이름
		UUID uuid = UUID.randomUUID();
		String ckUploadPath =  uuid + "_" + upload.getOriginalFilename();

		// 폴더 생성
		File f = new File(folder);

		if (!f.exists()) {
			f.mkdirs();
		}

		try {

			byte[] bytes = upload.getBytes();

			out = new FileOutputStream(new File(folder, ckUploadPath));
			out.write(bytes);
			out.flush(); // out에 저장된 데이터를 전송하고 초기화

			printWriter = response.getWriter();
			//String fileUrl = "localhost:8080/exam/board/ckUploadSubmit?uuid=" + uuid + "&fileName=" + upload.getOriginalFilename(); // 작성화면
			String fileUrl = "/qnaImg/"+ new SimpleDateFormat("yyyy/MM/dd").format(new Date()) +"/"+ ckUploadPath;
		
			
			// 업로드시 메시지 출력
			printWriter.println("{\"filename\" : \""+ckUploadPath+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
			printWriter.flush();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
