package com.group.exam.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.group.exam.admin.command.AdminQuestionMember;
import com.group.exam.admin.service.AdminService;
import com.group.exam.question.vo.QuestionRegistCommand;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
@RequestMapping(value="/admin/*")
public class AdminQuestionController {

	private AdminService adminService;

	@Autowired
	public AdminQuestionController(AdminService adminService) {
		
		this.adminService = adminService;
	}
	
	
	//status가 'F', 승인 대기중인 question 출력
	@RequestMapping(value="/question/list")
	public String questionList(Model model, Criteria cri, QuestionRegistCommand questionRegistCommand) {
		List<AdminQuestionMember> questions = adminService.questionList(cri);
		model.addAttribute("questions", questions);
		
		PagingVo pageMaker = new PagingVo();
		System.out.println(questions);
		pageMaker.setCri(cri);
		int questionTotal = adminService.questionListTotal();
		pageMaker.setTotalCount(questionTotal);
		
		model.addAttribute("boardTotal", questionTotal);
		model.addAttribute("pageMaker", pageMaker);
		return "/admin/questionList";
	}
	
	
	//status를 'T', 승인 ok로 바꾸기
	@RequestMapping("/question/approve")
	public String questionApprove(@RequestParam("questionSeq") Long questionSeq) {
		adminService.questionApprove(questionSeq);
		return "redirect:/admin/question/list";
	}
	//대기 중인 질문 삭제
	@RequestMapping("/question/delete")
	public String questionDelete(@RequestParam("questionSeq") Long questionSeq) {
		adminService.questionDelete(questionSeq);
		return "redirect:/admin/question/list";
	}
	//질문 추가하기
	@RequestMapping(value ="/question/questionAdd" , method=RequestMethod.POST)
	public String questionAdd(@ModelAttribute("questionRegistCommand") @Valid QuestionRegistCommand questionRegistCommand
			,BindingResult result, Model model) {
		if(result.hasErrors()) {
				
			return "redirect:/admin/question/list";
		}
		adminService.addQuestion(questionRegistCommand);
		System.out.println(questionRegistCommand);
		return "redirect:/admin/question/list";
	}
	//총 질문 LIST
	@RequestMapping(value="/questionAll")
	public String questionAll(Model model, Criteria cri) {
		List<AdminQuestionMember> questionAll = adminService.questionAllList(cri);
		int questionTotal = adminService.countQuestionList();
		model.addAttribute("questionTotal", questionTotal);
		model.addAttribute("questions", questionAll);
		
		
		PagingVo pageMaker = new PagingVo();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(questionTotal);
		
		model.addAttribute("boardTotal", questionTotal);
		model.addAttribute("pageMaker", pageMaker);

		return "/admin/questionAllList";
	}

//	@Scheduled(cron = "0 * * * * *")
//	public void getSequence() {
//		logger.info(new Date() + "스케쥴러 실행");
//		num = adminService.getSequence();
//		System.out.println(num);
//	}
//	
//	@RequestMapping(value="todayQuestion")
//	public String todayQuestion (Model model) throws Exception {
//		if(num == 0) {
//			num = adminService.currentSequence();
//			
//		}
//		logger.info(""+num);
//		QuestionVo list = adminService.questionselect(num);
//		model.addAttribute("list", list);
//		return "/admin/todayQuestion";
//	}
}
