package com.group.exam.diary.command;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class DiaryUpdateCommand {
	private Long diarySeq;
	private Long memberSeq;
	@NotBlank(message="제목을 입력해 주세요")
	private String diaryTitle;
	@NotEmpty(message="내용을 입력해 주세요")
	private String diaryContent;
	private String diaryOpen;
	private MultipartFile img;
	private String diaryImg;
	

	
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

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
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
	public String getDiaryOpen() {
		return diaryOpen;
	}
	public void setDiaryOpen(String diaryOpen) {
		this.diaryOpen = diaryOpen;
	}
	@Override
	public String toString() {
		return "DiaryUpdateCommand [diarySeq=" + diarySeq + ", memberSeq=" + memberSeq + ", diaryTitle=" + diaryTitle
				+ ", diaryContent=" + diaryContent + ", diaryOpen=" + diaryOpen + ", img=" + img + ", diaryImg="
				+ diaryImg + "]";
	}
	
	
	
	
}
