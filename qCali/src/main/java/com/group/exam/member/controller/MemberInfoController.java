package com.group.exam.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.exam.member.command.MemberInfoCommand;
import com.group.exam.member.service.MemberService;

@Controller
public class MemberInfoController {
	
	private MemberService memberService;
	@Autowired
	public MemberInfoController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@RequestMapping(value="/member/popup")
	public String memberInfo(@RequestParam Long memberSeq, Model model) {
		
		MemberInfoCommand memberInfo = memberService.memberInfo(memberSeq);
		model.addAttribute("info", memberInfo);
		
		return "/member/memberInfo";
	}
	
}
