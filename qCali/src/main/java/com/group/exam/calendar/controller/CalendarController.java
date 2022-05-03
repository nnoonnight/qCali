package com.group.exam.calendar.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group.exam.calendar.service.CalendarServiceImpl;
import com.group.exam.calendar.vo.CalendarVo;

@Controller
public class CalendarController {
	private CalendarServiceImpl calendarService;
	@Autowired
	public CalendarController(CalendarServiceImpl calendarService) {
		this.calendarService = calendarService;
	}
	
	@RequestMapping(value="/calendar", method=RequestMethod.GET)
	public String getCalendarList(Model model) {
		List<CalendarVo> list = null;
		list = calendarService.calendarList();
		
		model.addAttribute("list", list);
		return "/calendar/calendar";
	}
	
	
}
