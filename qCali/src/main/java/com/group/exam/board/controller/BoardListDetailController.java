package com.group.exam.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.exam.board.command.BoardLikeCommand;
import com.group.exam.board.command.BoardlistCommand;
import com.group.exam.board.command.BoardreplyInsertCommand;
import com.group.exam.board.service.BoardService;
import com.group.exam.board.vo.BoardHeartVo;
import com.group.exam.board.vo.ReplyVo;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.service.MemberService;
@Controller
public class BoardListDetailController {


	private BoardService boardService;

	private MemberService memberService;

	@Autowired
	public BoardListDetailController(BoardService boardService, MemberService memberService) {
		
		this.boardService = boardService;
		this.memberService = memberService;
	}



	// 게시글 디테일
	@GetMapping(value = "/board/detail")
	public String boardListDetail(@RequestParam Long boardSeq, Model model, HttpSession session) {

		boardService.boardCountup(boardSeq);

		BoardlistCommand list = boardService.boardListDetail(boardSeq);
		boolean myArticle = false;
		// 세션 값 loginMember에 저장

		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");

		// 로그인 세션 없을 때 ->main
		if (loginMember == null) {
			model.addAttribute("msg", "로그인이 후에 이용 가능합니다.");
			return "member/member_alert/alertGoMain";
		}

		if (loginMember != null) {
			// 세션에서 멤버의 mSeq 를 boardVo에 셋팅
			Long memberSeq = loginMember.getMemberSeq();
			// 세션에 저장된 mSeq와 게시글의 mSeq를 비교하여 내 글이면 수정 삭제 버튼이 뜨게
			if (memberSeq == list.getMemberSeq()) {
				myArticle = true;
			}

			model.addAttribute("myArticle", myArticle);
		}

		model.addAttribute("boardList", list);
		model.addAttribute("boardSeq", boardSeq);

		BoardHeartVo likeVo = new BoardHeartVo();

		likeVo.setBoardSeq(boardSeq);
		likeVo.setMemberSeq(loginMember.getMemberSeq());

		int boardlike = boardService.getBoardLike(likeVo);

		model.addAttribute("boardHeart", boardlike);

		return "board/listDetail";
	}

	@PostMapping(value = "/board/heart", produces = "application/json")
	@ResponseBody
	public int boardLike(@RequestBody BoardLikeCommand command, HttpSession session) {

		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");

		BoardHeartVo likeVo = new BoardHeartVo();

		likeVo.setBoardSeq(command.getBoardSeq());
		likeVo.setMemberSeq(loginMember.getMemberSeq());

		if (command.getHeart() >= 1) {
			boardService.deleteBoardLike(likeVo);
			command.setHeart(0);
		} else {

			boardService.insertBoardLike(likeVo);
			command.setHeart(1);
		}

		// String result = Integer.toString(heart);

		return command.getHeart();

	}

	// 댓글 list
	@PostMapping(value = "/board/reply/{boardSeq}")
	@ResponseBody
	public List<ReplyVo> boardReply(@PathVariable Long boardSeq, HttpSession session, Model model) {
		List<ReplyVo> replyList = boardService.replyList(boardSeq);
		// logger.info(replyList.toString());
		return replyList;
	}

	// 댓글 insert
	@PostMapping(value = "/board/replyInsert", produces = "application/json")
	@ResponseBody
	public void replyInsert(@RequestBody BoardreplyInsertCommand command, HttpSession session) {
		LoginCommand loginMember = (LoginCommand) session.getAttribute("memberLogin");

		ReplyVo insertReply = new ReplyVo();
		insertReply.setBoardSeq(command.getBoardSeq());
		insertReply.setMemberSeq(loginMember.getMemberSeq());
		insertReply.setReplyContent(command.getReplyContent());

		boardService.replyInsert(insertReply);
	}

	// 댓글 update
	@PostMapping(value = "/board/replyUpdate/{replySeq}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> replyUpdate(@RequestBody BoardreplyInsertCommand command, @PathVariable Long replySeq) {
		Map<String, Object> map = new HashMap<String, Object>();

		ReplyVo updateReply = new ReplyVo();
		updateReply.setReplySeq(replySeq);
		updateReply.setReplyContent(command.getReplyContent());

		boardService.replyUpdate(updateReply);
		map.put("result", "success");

		return map;
	}

	// 댓글 delete
	@PostMapping(value = "/board/replydelete/{replySeq}", produces = "application/json")
	@ResponseBody
	public Map<String, Object> replyDelete(@PathVariable Long replySeq) {
		Map<String, Object> map = new HashMap<String, Object>();

		ReplyVo deleteReply = new ReplyVo();
		deleteReply.setReplySeq(replySeq);

		boardService.replyDelete(deleteReply);
		map.put("result", "success");

		return map;
	}

}
