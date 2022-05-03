package com.group.exam.notice.command;

import org.hibernate.validator.constraints.NotEmpty;

public class NoticeUpdateCommand {
	@NotEmpty
	private String noticeTitle;
	@NotEmpty
	private String noticeContent;
	private Long noticeSeq;

	public Long getNoticeSeq() {
		return noticeSeq;
	}

	public void setNoticeSeq(Long noticeSeq) {
		this.noticeSeq = noticeSeq;
	}

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

	@Override
	public String toString() {
		return "NoticeUpdateCommand [noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent + ", noticeSeq="
				+ noticeSeq + "]";
	}

}
