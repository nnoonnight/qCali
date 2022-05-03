package com.group.exam.admin.command;

public class AdminAuthInfoCommand {
	private String adminId;
	private String adminNickname;
	private Long adminSeq;
	
	
	public AdminAuthInfoCommand() {
		super();
		// TODO Auto-generated constructor stub
	}


	public AdminAuthInfoCommand(String adminId, String adminNickname, Long adminSeq) {
		super();
		this.adminId = adminId;
		this.adminNickname = adminNickname;
		this.adminSeq = adminSeq;
	}


	public String getAdminId() {
		return adminId;
	}


	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}


	public String getAdminNickname() {
		return adminNickname;
	}


	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}


	public Long getAdminSeq() {
		return adminSeq;
	}


	public void setAdminSeq(Long adminSeq) {
		this.adminSeq = adminSeq;
	}
	
	
}
