package com.group.exam.admin.vo;

import java.util.Date;

public class AdminMemberListVo {
	private String memberId;
	private String memberNickName;
	private Date memberRegday;
	private String memberAuthStatus;
	private int memberLevel;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public Date getMemberRegday() {
		return memberRegday;
	}
	public void setMemberRegday(Date memberRegday) {
		this.memberRegday = memberRegday;
	}
	public String getMemberAuthStatus() {
		return memberAuthStatus;
	}
	public void setMemberAuthStatus(String memberAuthStatus) {
		this.memberAuthStatus = memberAuthStatus;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
	
	
	
}
