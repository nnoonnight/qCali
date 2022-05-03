package com.group.exam.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.exam.admin.command.AdminBoardCommand;
import com.group.exam.admin.service.AdminService;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
@RequestMapping("/admin/board/*")
public class AdminBoardController {
	private AdminService adminService;
	
	@Autowired
	public AdminBoardController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, Criteria cri) throws Exception{
		model.addAttribute("boards", adminService.boardList(cri));
		
		PagingVo pageMaker = new PagingVo();
		
		pageMaker.setCri(cri);
		int boardTotal = adminService.boardListTotal();
		pageMaker.setTotalCount(boardTotal);
		
		model.addAttribute("boardTotal", boardTotal);
		model.addAttribute("pageMaker", pageMaker);
		
		return "/admin/boardList";
	}

	
	@RequestMapping(value="/detail/{boardSeq}")
	public String detail(@PathVariable("boardSeq") Long boardSeq, Model model) {
		AdminBoardCommand boards = adminService.selectBybseq(boardSeq);
		if(boards ==null) {
			//예외처리
		}
		model.addAttribute("boards", boards);
		return "/admin/boarddetail";
	}
	@RequestMapping(value="/delete" ,method=RequestMethod.GET)
	public String boardDelete(@RequestParam("boardSeq")Long boardSeq) {
		adminService.boardDelete(boardSeq);
		return "redirect:/admin/board/list";
	}

}
