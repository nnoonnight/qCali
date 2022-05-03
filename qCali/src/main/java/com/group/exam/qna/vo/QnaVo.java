package com.group.exam.qna.vo;

import org.springframework.web.multipart.MultipartFile;

public class QnaVo {
	private Long qnaSeq;
	private int qnaReadcnt;
	private int qnaNo;
	private int qnaStep;
	private int qnaIndent;
	private int qnaRoot;

	private String qnaTitle;
	private String qnaContent;
	private String qnaFileName;
	private String qnaFilePath;
	private String qnaName;
	private String qnaRegDay;
	private String qnaWriter;

	private Long memberSeq;
	private String memberNickname;
	private MultipartFile uploadfile;

	public String getQnaRegDay() {
		return qnaRegDay;
	}

	public void setQnaRegDay(String qnaRegDay) {
		this.qnaRegDay = qnaRegDay;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public QnaVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQnaRoot() {
		return qnaRoot;
	}

	public void setQnaRoot(int qnaRoot) {
		this.qnaRoot = qnaRoot;
	}

	public MultipartFile getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public Long getQnaSeq() {
		return qnaSeq;
	}

	public void setQnaSeq(Long qnaSeq) {
		this.qnaSeq = qnaSeq;
	}

	public int getQnaReadcnt() {
		return qnaReadcnt;
	}

	public void setQnaReadcnt(int qnaReadcnt) {
		this.qnaReadcnt = qnaReadcnt;
	}

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public int getQnaStep() {
		return qnaStep;
	}

	public void setQnaStep(int qnaStep) {
		this.qnaStep = qnaStep;
	}

	public int getQnaIndent() {
		return qnaIndent;
	}

	public void setQnaIndent(int qnaIndent) {
		this.qnaIndent = qnaIndent;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}

	public String getQnaFileName() {
		return qnaFileName;
	}

	public void setQnaFileName(String qnaFileName) {
		this.qnaFileName = qnaFileName;
	}

	public String getQnaFilePath() {
		return qnaFilePath;
	}

	public void setQnaFilePath(String qnaFilePath) {
		this.qnaFilePath = qnaFilePath;
	}

	public String getQnaName() {
		return qnaName;
	}

	public void setQnaName(String qnaName) {
		this.qnaName = qnaName;
	}

	public Long getMemberSeq() {
		return memberSeq;
	}

	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}

	@Override
	public String toString() {
		return "QnaVo [qnaSeq=" + qnaSeq + ", qnaReadcnt=" + qnaReadcnt + ", qnaNo=" + qnaNo + ", qnaStep=" + qnaStep
				+ ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", qnaFileName=" + qnaFileName
				+ ", qnaFilePath=" + qnaFilePath + ", qnaName=" + qnaName + ", qnaWriter=" + qnaWriter + ", uploadFile="
				+ ", memberSeq=" + memberSeq + "]";
	}

}
