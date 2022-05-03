package com.group.exam.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.exam.admin.service.AdminService;
import com.group.exam.member.vo.MemberVo;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
@RequestMapping(value="/admin/member/*")
public class AdminMemberController {
	private AdminService adminService;
	
	@Autowired
	public AdminMemberController(AdminService adminService) {
		this.adminService= adminService;
	}
	
	//멤버 리스트
	@RequestMapping(value="/list")
	public String list(Model model, Criteria cri) {
		List<MemberVo> members = adminService.selectMember(cri);
		model.addAttribute("members", members);
		
		PagingVo pageMaker = new PagingVo();
		pageMaker.setCri(cri);
		int boardTotal = adminService.memberListTotal();
		pageMaker.setTotalCount(boardTotal);
		model.addAttribute("boardTotal", boardTotal);
		model.addAttribute("pageMaker", pageMaker);

		
		
		return"/admin/memberList";
	}
	
	//유저 관리
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String memberDelete(@RequestParam("memberSeq") Long memberSeq) {
		adminService.memberDelete(memberSeq);
		return "redirect:/admin/member/list";
	}
	
}
