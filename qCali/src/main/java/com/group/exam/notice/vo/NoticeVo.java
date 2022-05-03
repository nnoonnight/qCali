package com.group.exam.notice.vo;

public class NoticeVo {
	private Long noticeSeq;
	private String noticeTitle;
	private String noticeContent;
	private String noticeRegDay;
	private Long adminSeq;

	public NoticeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeVo(String noticeTitle, String noticeContent) {
		super();
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	public Long getNoticeSeq() {
		return noticeSeq;
	}

	public void setNoticeSeq(Long noticeSeq) {
		this.noticeSeq = noticeSeq;
	}

	public Long getAdminSeq() {
		return adminSeq;
	}

	public void setAdminSeq(Long adminSeq) {
		this.adminSeq = adminSeq;
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

	public String getNoticeRegDay() {
		return noticeRegDay;
	}

	public void setNoticeRegDay(String noticeRegDay) {
		this.noticeRegDay = noticeRegDay;
	}

	@Override
	public String toString() {
		return "NoticeVo [noticeSeq=" + noticeSeq + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeRegDay=" + noticeRegDay + ", adminSeq=" + adminSeq + "]";
	}

}
