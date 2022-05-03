package com.group.exam.member.vo;

import java.util.Date;

public class MemberVo {
	private Long memberSeq;

	private String memberId;
	private String memberBpw;
	private String memberPassword;
	private String memberNickname;
	private String memberBirthDay;
	private String memberRegDay;
	private String memberAuth;
	private String memberAuthkey;
	private int memberLevel;
	
	private String naver;
	private String kakao;
	public Long getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberBpw() {
		return memberBpw;
	}
	public void setMemberBpw(String memberBpw) {
		this.memberBpw = memberBpw;
	}
	public String getMemberPassword() {
		return memberPassword;
	}
	public void setMemberPassword(String memberPassword) {
		this.memberPassword = memberPassword;
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
	public String getMemberRegDay() {
		return memberRegDay;
	}
	public void setMemberRegDay(String memberRegDay) {
		this.memberRegDay = memberRegDay;
	}
	public String getMemberAuth() {
		return memberAuth;
	}
	public void setMemberAuth(String memberAuth) {
		this.memberAuth = memberAuth;
	}
	public String getMemberAuthkey() {
		return memberAuthkey;
	}
	public void setMemberAuthkey(String memberAuthkey) {
		this.memberAuthkey = memberAuthkey;
	}
	public int getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}
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
	@Override
	public String toString() {
		return "MemberVo [memberSeq=" + memberSeq + ", memberId=" + memberId + ", memberBpw=" + memberBpw
				+ ", memberPassword=" + memberPassword + ", memberNickname=" + memberNickname + ", memberBirthDay="
				+ memberBirthDay + ", memberRegDay=" + memberRegDay + ", memberAuth=" + memberAuth + ", memberAuthkey="
				+ memberAuthkey + ", memberLevel=" + memberLevel + ", naver=" + naver + ", kakao=" + kakao + "]";
	}
	
	
	

}
