package com.group.exam.admin.command;

public class AdminQuestionMember {
	private Long no;
	private Long questionSeq;
	private String questionContent;
	private String memberNickname;
	private Long memberSeq;
	private String questionStatus;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getQuestionSeq() {
		return questionSeq;
	}
	public void setQuestionSeq(Long questionSeq) {
		this.questionSeq = questionSeq;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public Long getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getQuestionStatus() {
		return questionStatus;
	}
	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}
	@Override
	public String toString() {
		return "AdminQuestionMember [no=" + no + ", questionSeq=" + questionSeq + ", questionContent=" + questionContent
				+ ", memberNickname=" + memberNickname + ", memberSeq=" + memberSeq + ", questionStatus="
				+ questionStatus + "]";
	}
	

	
}
