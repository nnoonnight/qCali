package com.group.exam.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group.exam.member.service.MemberService;

@Controller
public class MemberEmailConfirmController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(value = "/member/EmailConfirm")
	public String signUpConfirm(HttpServletRequest request, HttpSession session) {
		String memberId = request.getParameter("email");
		String memberAuthkey = request.getParameter("authKey");
		if (memberService.idDup(memberId) == 1) { // 가입된 이메일이 있다면
			memberService.updateAuth(memberAuthkey); // mAuth를 'T'로 바꾸기

		}
		if (session.getAttribute("memberLogin") != null) {

			session.invalidate(); // 로그인 값 있으면 로그아웃
		}
		return "/member/member_alert/signUpSuccess";
	}

}
