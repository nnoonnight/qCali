package com.group.exam.diary.vo;

public class DiaryHeartVo {
	
	private Long heartSeq;
	private Long diarySeq;
	private Long memberSeq;
	
	
	public Long getHeartSeq() {
		return heartSeq;
	}


	public void setHeartSeq(Long heartSeq) {
		this.heartSeq = heartSeq;
	}


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


	@Override
	public String toString() {
		return "DiaryHeartVo [heartSeq=" + heartSeq + ", diarySeq=" + diarySeq + ", memberSeq=" + memberSeq + "]";
	}
	

	
	
	
	
	

}
