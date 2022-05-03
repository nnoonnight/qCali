package com.group.exam.member.command;

import org.hibernate.validator.constraints.NotBlank;


public class MemberchangePwd {
	@NotBlank(message = "비밀번호를 입력하세요.")
	private String memberPassword;
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memberPasswordCheck;

	
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
	}
	public String getMemberPasswordCheck() {
		return memberPasswordCheck;
	}
	public void setMemberPasswordCheck(String memberPasswordCheck) {
		this.memberPasswordCheck = memberPasswordCheck;
	}
	@Override
	public String toString() {
		return "MemberchangePwd [memberPassword=" + memberPassword + ", memberPasswordCheck=" + memberPasswordCheck
				+ "]";
	}

	


}
