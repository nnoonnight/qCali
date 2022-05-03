package com.group.exam.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group.exam.qna.vo.QnaVo;
import com.group.exam.utils.Criteria;

@Repository
public class QnaDaoImpl implements QnaDao{
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	public QnaDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate= sqlSessionTemplate;
	}

	@Override
	public void qnaInsert(QnaVo vo) {
		sqlSessionTemplate.insert("qnaInsert", vo);
	}

	@Override
	public List<QnaVo> list(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("qnaList",cri);
	}

	@Override
	public QnaVo detail(Long qnaSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("qnaDetail", qnaSeq);
	}

	@Override
	public void qnaUpdate(QnaVo vo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("qnaUpdate", vo);
	}

	@Override
	public void qnaDelete(Long qnaSeq) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("qnaDelete", qnaSeq);
	}

	@Override
	public void qnaRead(Long qnaSeq) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("qnaReadCount", qnaSeq);
	}

	@Override
	public void qnaReplyInsert(QnaVo vo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("qnaReplyInsert", vo);
	}

	@Override
	public int qnaTotalList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("qnaTotalList");
	}
}
