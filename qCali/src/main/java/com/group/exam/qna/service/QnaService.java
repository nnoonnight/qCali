package com.group.exam.qna.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.group.exam.qna.vo.QnaVo;
import com.group.exam.utils.Criteria;

public interface QnaService {
	
	public void write(QnaVo vo);
	public List<QnaVo> list(Criteria cri);
	public QnaVo detail(Long qnaSeq);
	public void qnaUpdate(QnaVo vo);
	public void qnaDelete(Long qnaSeq);
	public void qnaRead(Long qnaSeq); //조회수 증가
	public void qnaReplyInsert(QnaVo vo);//답글
	public int qnaTotalList(); //Total값(페이징 처리)
	
	//다운로드& 업로드 메소드
	public String upload(String category, MultipartFile file, HttpSession session);
	public File download(String filename, String filepath, HttpSession session, HttpServletResponse response);
}
