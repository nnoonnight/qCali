package com.group.exam.diary.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class DiaryVo {
	
	private Long diarySeq;
	@NotEmpty(message="제목을 입력해주세요.")
	private String diaryTitle;
	@NotEmpty(message="내용을 입력해주세요.")
	private String diaryContent;
	private String diaryRegday;
	private String diaryOpen;
	private Long memberSeq;
	private int diaryLike;
	private int diaryCount;
	private MultipartFile img;
	private String diaryImg;

	
	
	public Long getDiarySeq() {
		return diarySeq;
	}
	public void setDiarySeq(Long diarySeq) {
		this.diarySeq = diarySeq;
	}
	public Long getMemberSeq() {
		return memberSeq;
	}
	public void setMemberSeq(Long memberSeq) {
		this.memberSeq = memberSeq;
	}
	public MultipartFile getImg() {
		return img;
	}
	public void setImg(MultipartFile img) {
		this.img = img;
	}
	public String getDiaryImg() {
		return diaryImg;
	}
	public void setDiaryImg(String diaryImg) {
		this.diaryImg = diaryImg;
	}

	public String getDiaryTitle() {
		return diaryTitle;
	}
	public void setDiaryTitle(String diaryTitle) {
		this.diaryTitle = diaryTitle;
	}
	public String getDiaryContent() {
		return diaryContent;
	}
	public void setDiaryContent(String diaryContent) {
		this.diaryContent = diaryContent;
	}
	public String getDiaryRegday() {
		return diaryRegday;
	}
	public void setDiaryRegday(String diaryRegday) {
		this.diaryRegday = diaryRegday;
	}
	public String getDiaryOpen() {
		return diaryOpen;
	}
	public void setDiaryOpen(String diaryOpen) {
		this.diaryOpen = diaryOpen;
	}

	public int getDiaryLike() {
		return diaryLike;
	}
	public void setDiaryLike(int diaryLike) {
		this.diaryLike = diaryLike;
	}
	public int getDiaryCount() {
		return diaryCount;
	}
	public void setDiaryCount(int diaryCount) {
		this.diaryCount = diaryCount;
	}
	@Override
	public String toString() {
		return "DiaryVo [diarySeq=" + diarySeq + ", diaryTitle=" + diaryTitle + ", diaryContent=" + diaryContent
				+ ", diaryRegday=" + diaryRegday + ", diaryOpen=" + diaryOpen + ", memberSeq=" + memberSeq
				+ ", diaryLike=" + diaryLike + ", diaryCount=" + diaryCount + ", img=" + img + ", diaryImg=" + diaryImg
				+ "]";
	}

	
	
	
	
	
	

}
