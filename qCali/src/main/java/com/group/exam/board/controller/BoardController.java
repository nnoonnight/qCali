package com.group.exam.board.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.group.exam.board.command.BoardlistCommand;
import com.group.exam.board.command.BoardupdateCommand;
import com.group.exam.board.command.QuestionAdayCommand;
import com.group.exam.board.service.BoardService;
import com.group.exam.board.vo.BoardVo;
import com.group.exam.board.vo.NoticeAdminVo;
import com.group.exam.calendar.service.CalendarService;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.service.MemberService;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	public static int num;

	private BoardService boardService;
	private MemberService memberService;
	private CalendarService calendarService;

	@Autowired
	public BoardController(BoardService boardService, MemberService memberService, CalendarService calendarService) {
		this.calendarService = calendarService;
		this.boardService = boardService;
		this.memberService = memberService;
	}

	@GetMapping(value = "/write")
	public String insertBoard(@ModelAttribute("boardData") BoardVo boardVo, HttpSession session, Model model) {

		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");
		// 로그인 X
		if (loginMember == null) {

			model.addAttribute("msg", "로그인이 후에 이용 가능합니다.");
			return "member/member_alert/alertGoMain";
		}

		// 이메일 인증 x
		if (boardService.memberAuth(loginMember.getMemberSeq()).equals("F")) {
			model.addAttribute("msg", "이메일 인증을 완료해주세요.");
			return "member/member_alert/alertGoBoardList";

		}

		return "board/writeForm";
	}

	@PostMapping(value = "/write")
	public String insertBoard(@Valid @ModelAttribute("boardData") BoardVo boardVo, BindingResult bindingResult,
			Criteria cri, HttpSession session, Model model) {
		// not null 체크
		if (bindingResult.hasErrors()) {

			return "board/writeForm";
		}

		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");

		// 세션에서 멤버의 mSeq 를 boardVo에 셋팅
		boardVo.setMemberSeq(loginMember.getMemberSeq());

		// insert
		boardService.insertBoard(boardVo);

		// calendar insert
		// boardSeq를 넣는다.
		Long currentSeq = boardService.currentBoardSeq();
		calendarService.insertCalendar(currentSeq);

		int mytotal = boardService.mylistCount(loginMember.getMemberSeq());

		if (mytotal > 10) {
			int memberLevel = boardService.memberLevelup(loginMember.getMemberSeq(), mytotal,
					loginMember.getMemberLevel());

			if (memberLevel == 1) {
				LoginCommand member = memberService.login(loginMember.getMemberId());
				LoginCommand login = member;
				session.setAttribute("memberLogin", login);
				model.addAttribute("msg", member.getMemberNickname() + "님 레벨이 " + member.getMemberLevel() + "변경되었습니다.");
				return "member/member_alert/alertGoBoardList";

			}
		}

		return "redirect:/board/list";
	}

	@PostMapping(value = "/ckUpload")
	public void ckUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) {

		OutputStream out = null;
		PrintWriter printWriter = null;

		// 인코딩
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 서버의 업로드할 물리적 위치
		String resources = "C:/dev1/workspacesQcali/resources/upload";
		String folder = resources + "/" + "board" + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());

		// 파일 이름
		UUID uuid = UUID.randomUUID();
		String ckUploadPath = uuid + "_" + upload.getOriginalFilename();

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

			// String callback = request.getParameter("CKEditorFuncNum");
			printWriter = response.getWriter();
			// String fileUrl = "localhost:8080/exam/board/ckUploadSubmit?uuid=" + uuid +
			// "&fileName=" + upload.getOriginalFilename(); // 작성화면
			String fileUrl = "/boardImg/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + "/" + ckUploadPath;

			// 업로드시 메시지 출력
			printWriter.println(
					"{\"filename\" : \"" + ckUploadPath + "\", \"uploaded\" : 1, \"url\":\"" + fileUrl + "\"}");
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

	// 리스트 전체
	@GetMapping(value = "/list")
	public String boardListAll(Criteria cri, Model model, HttpSession session) {

		/*
		 * @RequestParam null 허용 방법 - (required = false) == true 가 기본 설정임 - @Nullable
		 * 어노테이션 추가
		 * 
		 * - int 형의 경우 (defaultValue="0")
		 * 
		 */

		int total = boardService.listCount();

		if (total == 0) {
			total = 1;
		}
		/*
		 * 1 1,10 2 11, 20
		 */

		List<BoardlistCommand> list = boardService.boardList(cri);

		model.addAttribute("boardList", list);

		PagingVo pageCommand = new PagingVo();
		pageCommand.setCri(cri);
		pageCommand.setTotalCount(total);
		model.addAttribute("pageMaker", pageCommand);
		model.addAttribute("boardTotal", total);

		//페이지처리 url
		model.addAttribute("lastUrl", "list");

		if (num == 0) {
			num = boardService.currentSequence();
			if (num == 0) {
				num = 1;
			}
		}
		logger.info("" + num);
		QuestionAdayCommand question = boardService.questionselect(num);

		model.addAttribute("boardQuestion", question);
		System.out.println(question);

		// 공지사항
		List<NoticeAdminVo> notice = boardService.noticelist();
		System.out.println(notice);
		model.addAttribute("notice", notice);

		return "board/list";
	}

	@Scheduled(cron = "0 0 12 1/1 * ?") // 하루마다 출력으로 표현식
	public void getSequence() {
		logger.info(new Date() + "스케쥴러 실행");
		num = boardService.getSequence();
	}

	// 게시글 수정
	@GetMapping(value = "/edit")
	public String boardEdit(@ModelAttribute("boardEditData") BoardVo boardVo, HttpSession session, Model model) {

		model.addAttribute("articleInfo", boardService.boardListDetail(boardVo.getBoardSeq()));
		return "board/editForm";
	}

	// 게시글 수정
	@PostMapping(value = "/edit")
	public String boardEdit(@Valid @ModelAttribute("boardEditData") BoardupdateCommand updateCommand,
			BindingResult bindingResult, Model model, HttpSession session) {

		if (bindingResult.hasErrors()) {

			return "board/editForm";
		}

		// 세션 값 loginMember에 저장
		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");

		if (loginMember != null) {
			// 세션에서 멤버의 mSeq 를 boardVo에 셋팅

			Long boardSeq = updateCommand.getBoardSeq();

			BoardlistCommand list = boardService.boardListDetail(boardSeq);

			model.addAttribute("boardList", list);
			boardService.updateBoard(updateCommand.getBoardTitle(), updateCommand.getBoardContent(), boardSeq);
			System.out.println(" 수정 성공");
		} else {

			model.addAttribute("msg", "수정 실패 \n 메인으로 이동합니다.");
			return "member/member_alert/alertGoMain";
		}

		return "redirect:/board/list";
	}

	// 게시글 삭제
	@GetMapping(value = "/delete")
	public String boardDelect(@RequestParam Long boardSeq, Model model, HttpSession session, Criteria cri) {

		// 세션 값 loginMember에 저장
		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");

		if (loginMember != null) {
			// 세션에서 멤버의 mSeq 를 boardVo에 셋팅
			Long memberSeq = loginMember.getMemberSeq();
			boardService.deleteBoardOne(boardSeq, memberSeq);
			System.out.println("삭제 성공");
		} else {
			model.addAttribute("msg", "삭제 실패 \n 메인으로 이동합니다.");
			return "member/member_alert/alertGoMain";
		}

		return "redirect:/board/list";
	}

	// 다른회원 or 내 글 모아보기
	@GetMapping(value = "/memberArticle")
	public String boardListMemberArticle(@RequestParam("memberSeq") Long memberSeq, Model model, Criteria cri,
			HttpSession session) {

		int total = boardService.mylistCount(memberSeq);

		List<BoardlistCommand> list = boardService.boardMyList(cri, memberSeq);
		model.addAttribute("boardList", list);

		PagingVo pageCommand = new PagingVo();
		pageCommand.setCri(cri);
		pageCommand.setTotalCount(total);
		model.addAttribute("boardTotal", total);
		model.addAttribute("pageMaker", pageCommand);

		model.addAttribute("searchMember", memberSeq);
		return "board/memberArticleList";
	}

	// 오늘 올라온 글 보기
	@GetMapping(value = "/todayArticle")
	public String boardListTodayArticle(Model model, Criteria cri, HttpSession session) {

		int total = boardService.boardTodayCount();

		List<BoardlistCommand> list = boardService.boardListTodayArticle(cri);
		model.addAttribute("boardList", list);

		PagingVo pageCommand = new PagingVo();
		pageCommand.setCri(cri);
		pageCommand.setTotalCount(total);
		model.addAttribute("boardTotal", total);
		model.addAttribute("pageMaker", pageCommand);


		// 페이지처리 url
		model.addAttribute("lastUrl", "todayArticle");

		// 질문 출력 관련
		if (num == 0) {
			num = boardService.currentSequence();
			if (num == 0) {
				num = 1;
			}
		}
		logger.info("" + num);
		QuestionAdayCommand question = boardService.questionselect(num);

		model.addAttribute("boardQuestion", question);
		System.out.println(question);

		// 공지사항
		List<NoticeAdminVo> notice = boardService.noticelist();
		System.out.println(notice);
		model.addAttribute("notice", notice);

		return "board/list";
	}

	// 닉네임 , 제목으로 검색
	@GetMapping(value = "/search")
	public String boardSearchList(@RequestParam("searchOption") String searchOption,
			@RequestParam("searchWord") String searchWord, Model model, Criteria cri) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("searchOption", searchOption);
		map.put("searchWord", searchWord);

		int total = boardService.boardSearchCount(map);

		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		List<BoardlistCommand> list = boardService.boardSearch(map);

		PagingVo pageCommand = new PagingVo();
		pageCommand.setCri(cri);
		pageCommand.setTotalCount(total);
		model.addAttribute("boardTotal", total);
		model.addAttribute("pageMaker", pageCommand);
		model.addAttribute("boardList", list);

		model.addAttribute("searchOption", searchOption);
		model.addAttribute("searchWord", searchWord);

		// 질문 출력

		QuestionAdayCommand question = boardService.questionselect(num);
		model.addAttribute("boardQuestion", question);

		return "/board/searchArticleList";
	}

}
