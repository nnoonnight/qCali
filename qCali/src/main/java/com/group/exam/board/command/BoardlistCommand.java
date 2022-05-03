package com.group.exam.board.command;

public class BoardlistCommand {
	private Long rn;
	private Long boardSeq;
	private String boardTitle;
	private String boardRegday;
	private int boardLike;
	private int boardCount;
	private String memberNickname;
	private String boardContent;
	private Long memberSeq;
	
	

	public Long getRn() {
		return rn;
	}
	public void setRn(Long rn) {
		this.rn = rn;
	}
	public Long getBoardSeq() {
		return boardSeq;
	}
	public void setBoardSeq(Long boardSeq) {
		this.boardSeq = boardSeq;
	}
	public Long getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardRegday() {
		return boardRegday;
	}
	public void setBoardRegday(String boardRegday) {
		this.boardRegday = boardRegday;
	}
	public int getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(int boardLike) {
		this.boardLike = boardLike;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	@Override
	public String toString() {
		return "BoardlistCommand [boardSeq=" + boardSeq + ", boardTitle=" + boardTitle + ", boardRegday=" + boardRegday
				+ ", boardLike=" + boardLike + ", boardCount=" + boardCount + ", memberNickname=" + memberNickname
				+ ", boardContent=" + boardContent + ", memberSeq=" + memberSeq + "]";
	}
		
	
	
	

}
