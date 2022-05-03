package com.group.exam.member.command;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Alias("insertCommand")
public class InsertCommand {
	@NotBlank(message="이메일을 입력하세요.")
	@Email(message="형식에 맞게 입력하세요.")
	private String memberId ;
	
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memberPassword;
	@NotBlank(message="비밀번호를 입력하세요.")
	private String memberPasswordCheck;
	@NotBlank(message="닉네임을 입력하세요.")
	private String memberNickname;
	@NotBlank(message="생년월일을 입력하세요.")
	private String memberBirthDay;
	
	private String memberAuthkey;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

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

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getMemberBirthDay() {
		return memberBirthDay;
	}

	public void setMemberBirthDay(String memberBirthDay) {
		this.memberBirthDay = memberBirthDay;
	}

	public String getMemberAuthkey() {
		return memberAuthkey;
	}

	public void setMemberAuthkey(String memberAuthkey) {
		this.memberAuthkey = memberAuthkey;
	}


	
	
	
	

}
