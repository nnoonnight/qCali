package com.group.exam.admin.command;

import org.hibernate.validator.constraints.NotEmpty;

public class AdminLoginCommand {
	
	@NotEmpty(message="아이디를 입력해주세요.")
	private String adminId;
	@NotEmpty(message="비밀번호를 입력해주세요.")
	private String adminPassword;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	

	
	
}
