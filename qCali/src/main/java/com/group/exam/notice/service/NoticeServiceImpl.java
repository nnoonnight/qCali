package com.group.exam.notice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.exam.notice.command.WriteCommand;
import com.group.exam.notice.dao.NoticeDao;
import com.group.exam.notice.vo.NoticeVo;
import com.group.exam.utils.Criteria;

@Service
public class NoticeServiceImpl implements NoticeService{

	private NoticeDao noticeDao;
	
	@Autowired
	public NoticeServiceImpl(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}
	
	@Override
	public List<NoticeVo> list(Criteria cri) {
		// TODO Auto-generated method stub
		return noticeDao.selectAll(cri);
	}

	@Override
	public NoticeVo detail(Long noticeSeq) {
		// TODO Auto-generated method stub
		return noticeDao.selectDetail(noticeSeq);
	}

	@Override
	public void write(WriteCommand writeCommand) {
		NoticeVo noticeVo = new NoticeVo(writeCommand.getNoticeTitle(),
				writeCommand.getNoticeContent());
		noticeDao.insert(noticeVo);
	}

	@Override
	public void delete(Long noticeSeq) {
		noticeDao.delete(noticeSeq);
	}

	@Override
	public void update(String noticeTitle, String noticeContent, Long noticeSeq) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("noticeTitle", noticeTitle);
		map.put("noticeContent",noticeContent);
		map.put("noticeSeq", noticeSeq);
		
		
		noticeDao.update(map);
	}

	@Override
	public int noticeTotal() {
		// TODO Auto-generated method stub
		return noticeDao.noticeTotal();
	}

	

}
