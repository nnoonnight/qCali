package com.group.exam.board.vo;

public class BoardHeartVo {

	private Long heartSeq;
	private Long boardSeq;
	private Long memberSeq;

	public Long getHeartSeq() {
		return heartSeq;
	}

	public void setHeartSeq(Long heartSeq) {
		this.heartSeq = heartSeq;
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

	@Override
	public String toString() {
		return "BoardHeartVo [heartSeq=" + heartSeq + ", boardSeq=" + boardSeq + ", memberSeq=" + memberSeq + "]";
	}

}
