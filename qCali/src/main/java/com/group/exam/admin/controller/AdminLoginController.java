package com.group.exam.admin.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.group.exam.admin.command.AdminAuthInfoCommand;
import com.group.exam.admin.command.AdminLoginCommand;
import com.group.exam.admin.exception.IdpasswordNotMatchingException;
import com.group.exam.admin.service.AdminService;
import com.group.exam.utils.SessionConfig;

@Controller
public class AdminLoginController {
	private AdminService adminService;

	@Autowired
	public AdminLoginController(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.GET)
	public String login(Model model, AdminLoginCommand adminLoginCommand, HttpSession session) {
		if(session.getAttribute("adminAuthInfoCommand") != null) {
			// 만약 세션이 있으면 main으로 redirect 하도록
			return "/admin/main";
		}
		model.addAttribute("AdminLoginCommand", adminLoginCommand);
		return "admin/loginForm";
	}

	@RequestMapping(value = "/admin/login", method = RequestMethod.POST)
	public String submit(@ModelAttribute("AdminLoginCommand") @Valid AdminLoginCommand adminLoginCommand,
			BindingResult result, HttpSession session, Model model) {
		try {
			if (result.hasErrors()) {
				// 빈값이면 form 다시 보여주도록
				return "/admin/loginForm";
			} else {
				// 로그인 Command의 값을 session에 담는다.
				AdminAuthInfoCommand adminAuthInfoCommand = adminService.authenticate(adminLoginCommand.getAdminId(),
						adminLoginCommand.getAdminPassword());
				//중복 로그인 방지
				String adminId = SessionConfig.getSessionidCheck("adminAuthInfoCommand",
						adminAuthInfoCommand.getAdminId());

				// 세션 만료 시간
				session.setMaxInactiveInterval(60 * 60);
				// 세션에 set
				session.setAttribute("adminAuthInfoCommand", adminAuthInfoCommand);

				return "/admin/main";
			}

		} catch (IdpasswordNotMatchingException e) {
			return "admin/loginForm";
		}
	}
}
