package com.group.exam.board.vo;

public class NoticeAdminVo {
	private String noticeTitle;
	private String adminNickname;
	private int rownum; 
	private String noticeRegDay;
	private String noticeContent;
	private long noticeSeq;
	
	
	public long getNoticeSeq() {
		return noticeSeq;
	}
	public void setNoticeSeq(long noticeSeq) {
		this.noticeSeq = noticeSeq;
	}
	
	public String getNoticeRegDay() {
		return noticeRegDay;
	}
	public void setNoticeRegDay(String noticeRegDay) {
		this.noticeRegDay = noticeRegDay;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	
	public String getAdminNickname() {
		return adminNickname;
	}
	public void setAdminNickname(String adminNickname) {
		this.adminNickname = adminNickname;
	}
	@Override
	public String toString() {
		return "NoticeAdminVo [noticeTitle=" + noticeTitle + ", adminNickname=" + adminNickname + ", rownum=" + rownum
				+ ", noticeRegDay=" + noticeRegDay + ", noticeContent=" + noticeContent + ", noticeSeq=" + noticeSeq
				+ "]";
	}
	
	
}
