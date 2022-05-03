package com.group.exam.notice.service;

import java.util.List;

import com.group.exam.notice.command.WriteCommand;
import com.group.exam.notice.vo.NoticeVo;
import com.group.exam.utils.Criteria;

public interface NoticeService {
	public List<NoticeVo> list(Criteria cri);
	public NoticeVo detail(Long noticeSeq);
	public void write(WriteCommand writeCommand);
	public void delete(Long noticeSeq);
	public void update(String noticeTitle, String noticeContent, Long noticeSeq);
	public int noticeTotal();
	
}
