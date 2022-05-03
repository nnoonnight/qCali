package com.group.exam.notice.dao;

import java.util.HashMap;
import java.util.List;

import com.group.exam.notice.vo.NoticeVo;
import com.group.exam.utils.Criteria;

public interface NoticeDao {
	public List<NoticeVo> selectAll(Criteria cri);
	public NoticeVo selectDetail(Long noticeSeq);
	public int noticeTotal();
	public void insert(NoticeVo noticeVo);
	public void update(HashMap<String, Object> map);
	public void delete(Long noticeSeq);

}
