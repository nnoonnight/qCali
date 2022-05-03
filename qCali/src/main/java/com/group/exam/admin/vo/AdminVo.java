package com.group.exam.admin.vo;

import com.group.exam.admin.exception.IdpasswordNotMatchingException;

public class AdminVo {
	private Long adminSeq;
	private String adminId;
	private String adminPassword;
	private String adminNickname;

	public AdminVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminVo(Long adminSeq, String adminId, String adminPassword, String adminNickname) {
		super();
		this.adminSeq = adminSeq;
		this.adminId = adminId;
		this.adminPassword = adminPassword;
		this.adminNickname = adminNickname;
	}

	public Long getAdminSeq() {
		return adminSeq;
	}

	public void setAdminSeq(Long adminSeq) {
		this.adminSeq = adminSeq;
	}

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

	public String getAdminNickname() {
		return adminNickname;
	}

	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}

	public boolean matchPassword(String pwd) {
		return this.adminPassword.equals(pwd);
	}

	public void changePassword(String oldpwd, String newpwd) {
		if (!adminPassword.equals(oldpwd))
			throw new IdpasswordNotMatchingException();
		this.adminPassword = newpwd;
	}
}
