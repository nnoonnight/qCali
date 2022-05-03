package com.group.exam.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.service.MemberService;

@Controller
public class MemberQuestionController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/member/questionAdd", method = RequestMethod.GET)
	public String questionAdd (HttpSession session) {
		return "/member/questionAddForm";
	}
	
	@RequestMapping(value = "/member/questionAdd", method = RequestMethod.POST)
	public String questionAdd (@RequestParam(required = false) String questionContent, HttpSession session, Model model) {
		
		if (questionContent == null) {
			model.addAttribute("msg", "내용을 입력해 주세요.");
			return "/member/questionAddForm";
		}
		
		LoginCommand command = (LoginCommand) session.getAttribute("memberLogin");
		
		int result = memberService.memberQuestionAdd(questionContent, command.getMemberSeq());
		
		if(result != 1) {
			return "errors/questionError"; //업데이트 에러페이지
		}
		
		return "/member/member_alert/questionAddNext";
	}
	
	
	
}
