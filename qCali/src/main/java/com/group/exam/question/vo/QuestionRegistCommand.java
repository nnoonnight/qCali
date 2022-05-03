package com.group.exam.question.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class QuestionRegistCommand {
	
	@NotEmpty(message = "값을 입력해주세요.")
	private String questionContent;

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	
	
}
