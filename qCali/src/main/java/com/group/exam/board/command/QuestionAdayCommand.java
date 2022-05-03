package com.group.exam.board.command;

public class QuestionAdayCommand {

	private String questionContent;
	private Long questionSeq;
	private String questionStatus;
	private Long memberSeq;

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

	public Long getQuestionSeq() {
		return questionSeq;
	}

	public void setQuestionSeq(Long questionSeq) {
		this.questionSeq = questionSeq;
	}

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	@Override
	public String toString() {
		return "QuestionAdayCommand [questionContent=" + questionContent + ", questionSeq=" + questionSeq
				+ ", questionStatus=" + questionStatus + ", memberSeq=" + memberSeq + "]";
	}

}
