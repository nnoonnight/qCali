package com.group.exam.diary.vo;

public class DiaryHeartVo {
	
	private Long dheartSeq;
	private Long diarySeq;
	private Long memberSeq;
	
	
	public Long getHeartSeq() {
		return dheartSeq;
	}


	public void setHeartSeq(Long dheartSeq) {
		this.dheartSeq = dheartSeq;
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
		return "DiaryHeartVo [dheartSeq=" + dheartSeq + ", diarySeq=" + diarySeq + ", memberSeq=" + memberSeq + "]";
	}
	

	
	
	
	
	

}
