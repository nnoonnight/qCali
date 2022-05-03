package com.group.exam.member.command;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginCommand {
	private Long memberSeq;
	@Email(message = "이메일 형식이 아닙니다.")
	@NotEmpty(message = "아이디를 입력해주세요.")
	private String memberId;
	@NotEmpty(message = "비밀번호를 입력해주세요.")
	private String memberPassword;

	private String memberBpw;

	private String memberNickname;
	private String memberBirthDay;
	private String memberRegDay;
	private String memberAuth;

	private int memberLevel;
	private String naver;
	private String kakao;

	public String getNaver() {
		return naver;
	}

	public void setNaver(String naver) {
		this.naver = naver;
	}

	public String getKakao() {
		return kakao;
	}

	public void setKakao(String kakao) {
		this.kakao = kakao;
	}

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	public void setMemberBirthDay(String memberBirthDay) {
		this.memberBirthDay = memberBirthDay;
	}

	public void setMemberRegDay(String memberRegDay) {
		this.memberRegDay = memberRegDay;
	}

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

	public String getMemberBpw() {
		return memberBpw;
	}

	public void setMemberBpw(String memberBpw) {
		this.memberBpw = memberBpw;
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

	public void setMemberBirthday(String memberBirthDay) {
		this.memberBirthDay = memberBirthDay;
	}

	public String getMemberRegDay() {
		return memberRegDay;
	}

	public void setMemberRegday(String memberRegDay) {
		this.memberRegDay = memberRegDay;
	}

	public String getMemberAuth() {
		return memberAuth;
	}

	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

}
