package com.group.exam.diary.dao;

import java.util.HashMap;
import java.util.List;

import com.group.exam.diary.command.DiaryListCommand;
import com.group.exam.diary.vo.DiaryHeartVo;
import com.group.exam.diary.vo.DiaryVo;
import com.group.exam.utils.Criteria;

public interface DiaryDao {
	
	
	public void insertDiary (DiaryVo diaryVo); 
	
	public void updateDiary (HashMap<String, Object> map);
	
	public void deleteDiary (HashMap<String, Object> map); 
	
	public void deleteDiaryImg (Long diarySeq); 
	
	public List<DiaryListCommand> diaryList(HashMap<String, Object> map);
		
	public DiaryListCommand diaryDetail (Long diarySeq);
	
	public void diaryCountup (Long diarySeq); 
	
	public int diaryListCount (Long memberSeq); // 내가 쓴 글 수 
	
	public String memberAuth (Long memberSeq); //멤버 Auth 상태 체크
	
	public String diaryNickname (Long memberSeq); //닉네임 표시용

	//좋아요 기능 관련
    public int getDiaryLike(DiaryHeartVo vo);

    public void insertDiaryLike(DiaryHeartVo vo);

    public void deleteDiaryLike(DiaryHeartVo vo);

    public void updateDiaryLike(Long diarySeq);

}
