package com.group.exam.diary.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.group.exam.diary.command.DiaryListCommand;
import com.group.exam.diary.vo.DiaryHeartVo;
import com.group.exam.diary.vo.DiaryVo;
import com.group.exam.utils.Criteria;

public interface DiaryService {


	public void insertDiary (DiaryVo diaryVo); 
	
	public void updateDiary (String diaryTitle, String diaryContent, Long diarySeq, String diaryOpen,String diaryImg);
	
	public void deleteDiary (Long diarySeq, Long memberSeq); 
	
	public void deleteDiaryImg (Long diarySeq);
	
	public List<DiaryListCommand> diaryList(Criteria cri,Long memberSeq);
		
	public DiaryListCommand diaryDetail (Long diarySeq);
	
	public void diaryCountup (Long diarySeq); 

	public String memberAuth (Long memberSeq);
	
	public int diaryListCount(Long memberSeq); // 게시글 쓴 글 수
	
	public String diaryNickname (Long memberSeq); //닉네임 표시용
	
	//좋아요 기능 관련
	public void insertDiaryLike(DiaryHeartVo vo);
	
	public void deleteDiaryLike(DiaryHeartVo vo);
	
	public int getDiaryLike(DiaryHeartVo vo);
	
	//업로드 다운로드 메소드
	public String upload(String category, MultipartFile file, HttpSession session);
    
}
