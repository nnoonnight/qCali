package com.group.exam.notice.controller;

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
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group.exam.admin.command.AdminAuthInfoCommand;
import com.group.exam.notice.command.NoticeUpdateCommand;
import com.group.exam.notice.command.WriteCommand;
import com.group.exam.notice.service.NoticeService;
import com.group.exam.notice.vo.NoticeVo;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
public class NoticeController {

	private NoticeService noticeService;

	@Autowired
	public NoticeController(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@RequestMapping("/notice/read/{noticeSeq}")
	public String noticeDetail(Model model, @PathVariable("noticeSeq") Long noticeSeq) {
		NoticeVo notice = noticeService.detail(noticeSeq);

		if (notice == null) {
			// 예외처리
		}

		model.addAttribute("notice", notice);
		return "/notice/detail";
	}

	@RequestMapping(value = "/notice/list")
	public String noticeList(Model model, Criteria cri) {
		List<NoticeVo> notice = noticeService.list(cri);
		model.addAttribute("notice", notice);

		int noticeTotal = noticeService.noticeTotal();
		PagingVo pageMaker = new PagingVo();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(noticeTotal);
		model.addAttribute("boardTotal", noticeTotal);
		model.addAttribute("pageMaker", pageMaker);

		return "/notice/list";
	}

	@RequestMapping(value = "/notice/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		if(session.getAttribute("adminAuthInfoCommand") == null) {
			return "/main";
		}
		return "/notice/write";
	}

	@RequestMapping(value = "/notice/write", method = RequestMethod.POST)
	public String write(@ModelAttribute("writeForm") @Valid WriteCommand writeCommand, HttpSession session,
			BindingResult bindingResult) {
		AdminAuthInfoCommand adminAuthInfoCommand = (AdminAuthInfoCommand) session.getAttribute("adminAuthInfoCommand");

		if (adminAuthInfoCommand == null) {
			return "redirect:/admin/main";
		} else {
			if (bindingResult.hasErrors()) {
				return "/notice/wrtie";
			}
			noticeService.write(writeCommand);
		}
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/notice/delete", method = RequestMethod.GET)
	public String noticeDelete(@RequestParam("noticeSeq") Long noticeSeq, HttpSession session) {
		if(session.getAttribute("adminAuthInfoCommand") == null) {
			return "/main";
		}

		noticeService.delete(noticeSeq);

		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/notice/update", method = RequestMethod.GET)
	public String noticeUpdate(@RequestParam("noticeSeq") Long noticeSeq, Model model, HttpSession session) {
		if(session.getAttribute("adminAuthInfoCommand") == null) {
			return "/main";
		}

		NoticeVo notice = noticeService.detail(noticeSeq);
		model.addAttribute("notice", notice);
//		model.addAttribute("NoticeUpdateCommand", new NoticeUpdateCommand());
		return "/notice/editForm";
	}

	@RequestMapping(value = "/notice/update", method = RequestMethod.POST)
	public String noticeUpdate(@Valid @ModelAttribute("NoticeUpdateCommand") NoticeUpdateCommand updateCommand,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/notice/editForm";
		}
		System.out.println(updateCommand);
		noticeService.update(updateCommand.getNoticeTitle(), updateCommand.getNoticeContent(),
				updateCommand.getNoticeSeq());
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/notice/fileupload", method = RequestMethod.POST)
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
		String folder =  resources + "/" + "notice" + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
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
			String fileUrl = "/noticeImg/"+ new SimpleDateFormat("yyyy/MM/dd").format(new Date()) +"/"+ ckUploadPath;
		
			
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
