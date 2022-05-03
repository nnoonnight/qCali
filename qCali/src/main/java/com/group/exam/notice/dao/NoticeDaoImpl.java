package com.group.exam.notice.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group.exam.notice.vo.NoticeVo;
import com.group.exam.utils.Criteria;

@Repository	
public class NoticeDaoImpl implements NoticeDao{

	
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	public NoticeDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	
	
	
	@Override
	public List<NoticeVo> selectAll(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("noticeSelectAll", cri);
	}
	
	@Override
	public NoticeVo selectDetail(Long noticeSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("noticeDetail", noticeSeq);
	}

	@Override
	public int noticeTotal() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("noticeTotal");
	}

	@Override
	public void insert(NoticeVo noticeVo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("noticeInsert",noticeVo);
	}

	@Override
	public void update(HashMap<String, Object> map) {
		sqlSessionTemplate.update("noticeUpdate",map);
		
	}

	@Override
	public void delete(Long noticeSeq) {
		sqlSessionTemplate.delete("noticeDelete",noticeSeq);
	}

}
