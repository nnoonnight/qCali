package com.group.exam.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminMainController {
	@RequestMapping("/admin/main")
	public String main() {
		return "/admin/main/main";
	}

}
