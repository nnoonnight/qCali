package com.group.exam.board.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Alias("BoardVo")
public class BoardVo {

	private Long boardSeq;
	@Length(min = 2, max = 30, message = "2자 이상, 30자 미만으로 입력해주세요.")
	@NotEmpty(message = "제목을 입력해주세요.")
	private String boardTitle;
	@NotEmpty(message = "내용을 입력해주세요.")
	private String boardContent;
	private Date boardRegday;
	private int boardLike;
	private int boardCount;
	private Long memberSeq;
	private Long questionSeq;

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Date getBoardRegday() {
		return boardRegday;
	}

	public void setBoardRegday(Date boardRegday) {
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

	public Long getQuestionSeq() {
		return questionSeq;
	}

	public void setQuestionSeq(Long questionSeq) {
		this.questionSeq = questionSeq;
	}

	@Override
	public String toString() {
		return "BoardVo [boardSeq=" + boardSeq + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardRegday=" + boardRegday + ", boardLike=" + boardLike + ", boardCount=" + boardCount
				+ ", memberSeq=" + memberSeq + ", questionSeq=" + questionSeq + "]";
	}

}
