package com.group.exam.calendar.vo;

public class CalendarBoardVo {
	private Long boardSeq;
	private String boardTitle;
	private String memberNickname;
	private Long boardCount;
	private Long boardLike;
	private String quesionContent;
	private String boardContent;
	private int rn;
	
	public Long getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(Long boardSeq) {
		this.boardSeq = boardSeq;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public Long getBoardCount() {
		return boardCount;
	}

	public void setBoardCount(Long boardCount) {
		this.boardCount = boardCount;
	}

	public Long getBoardLike() {
		return boardLike;
	}

	public void setBoardLike(Long boardLike) {
		this.boardLike = boardLike;
	}

	public String getQuesionContent() {
		return quesionContent;
	}

	public void setQuesionContent(String quesionContent) {
		this.quesionContent = quesionContent;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public int getRn() {
		return rn;
	}

	public void setRn(int rn) {
		this.rn = rn;
	}

	@Override
	public String toString() {
		return "CalendarBoardVo [boardSeq=" + boardSeq + ", boardTitle=" + boardTitle + ", memberNickname="
				+ memberNickname + ", boardCount=" + boardCount + ", boardLike=" + boardLike + ", quesionContent="
				+ quesionContent + ", boardContent=" + boardContent + "]";
	}
	
}
