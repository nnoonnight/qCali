package com.group.exam.question.vo;

public class QuestionVo {
	private Long questionSeq;
	private String questionContent;
	private String questionStatus;
	private Long memberSeq;
	
	

	public QuestionVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QuestionVo(String questionContent) {
		super();
		this.questionContent = questionContent;
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
	public String getQuestionStatus() {
		return questionStatus;
	}
	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}
	public Long getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	
	
}
