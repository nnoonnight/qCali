package com.group.exam.notice.command;

import org.hibernate.validator.constraints.NotEmpty;

public class WriteCommand {
	@NotEmpty
	private String noticeTitle;
	@NotEmpty
	private String noticeContent;

	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
}
