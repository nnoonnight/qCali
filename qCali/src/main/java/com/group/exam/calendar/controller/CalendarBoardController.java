package com.group.exam.calendar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.group.exam.calendar.service.CalendarServiceImpl;
import com.group.exam.calendar.vo.CalendarBoardVo;
import com.group.exam.utils.Criteria;
import com.group.exam.utils.PagingVo;

@Controller
public class CalendarBoardController {
	private CalendarServiceImpl calendarService;
	
	@Autowired
	public CalendarBoardController(CalendarServiceImpl calendarService) {
		this.calendarService = calendarService;
	}
	
	@RequestMapping(value="/board/listDay", method=RequestMethod.POST)
	@ResponseBody
	public String listDay(@RequestParam String date) {
		System.out.println(date);
		return date;
	}
	
	
	@RequestMapping(value="/board/listDay", method=RequestMethod.GET)
	public String listDay(String date, Model model, Criteria cri) {		
		System.out.println("controller 옴");
		System.out.println("controller에서 "+date);
		model.addAttribute("date", date);
		
		List<CalendarBoardVo> boards = calendarService.boardByDate(cri, date);
		System.out.println(boards);
		model.addAttribute("boards", boards);
		
		
		PagingVo pageMaker = new PagingVo();
		pageMaker.setCri(cri);
		int boardTotal = calendarService.boardByDateTotal(date);
		pageMaker.setTotalCount(boardTotal);
		
		model.addAttribute("boardTotal", boardTotal);
		model.addAttribute("pageMaker", pageMaker);
		
//		try {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			System.out.println(dateFormat.parse(date));
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return "/board/boardByDate";
	}
	
	
}
