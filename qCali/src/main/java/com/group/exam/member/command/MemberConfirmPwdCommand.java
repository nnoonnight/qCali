package com.group.exam.member.command;

public class MemberConfirmPwdCommand {
	private String memberPassword;
	private String memberBpw;
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberBpw() {
		return memberBpw;
	}
	public void setMemberBpw(String memberBpw) {
		this.memberBpw = memberBpw;
	}
	@Override
	public String toString() {
		return "MemberConfirmPwdCommand [memberPassword=" + memberPassword + ", memberBpw=" + memberBpw + "]";
	}
	

}
