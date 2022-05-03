package com.group.exam.diary.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group.exam.diary.command.DiaryListCommand;
import com.group.exam.diary.vo.DiaryHeartVo;
import com.group.exam.diary.vo.DiaryVo;
import com.group.exam.utils.Criteria;
@Repository
public class DiaryDaoImpl implements DiaryDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insertDiary(DiaryVo diaryVo) {
		sqlSessionTemplate.insert("insertDiary", diaryVo);
	}

	@Override
	public void updateDiary(HashMap<String, Object> map) {
		sqlSessionTemplate.update("updateDiary",map);
	}

	@Override
	public void deleteDiary(HashMap<String, Object> map) {
		sqlSessionTemplate.delete("deleteDiary",map);
	}

	@Override
	public void deleteDiaryImg (Long diarySeq) {
		sqlSessionTemplate.update("deleteDiaryImg", diarySeq);		
	}
	@Override
	public List<DiaryListCommand> diaryList(HashMap<String, Object> map) {
		return sqlSessionTemplate.selectList("diaryList",map);
	}


	@Override
	public DiaryListCommand diaryDetail(Long diarySeq) {
		return sqlSessionTemplate.selectOne("diaryDetail", diarySeq);
	}

	@Override
	public void diaryCountup(Long diarySeq) {
		sqlSessionTemplate.update("diaryCountup", diarySeq);
		
	}
	
	@Override
	public int diaryListCount (Long memberSeq) {
		return sqlSessionTemplate.selectOne("diaryListCount", memberSeq);
		
	}
	
	@Override
	public String memberAuth(Long memberSeq) {
		return sqlSessionTemplate.selectOne("memberAuth", memberSeq);
	}
	
	@Override
	public String diaryNickname (Long memberSeq) {
		return sqlSessionTemplate.selectOne("diaryNickname", memberSeq);
	}
	
	//좋아요 기능 관련
		@Override
		public int getDiaryLike(DiaryHeartVo vo) {
			// TODO Auto-generated method stub
			return sqlSessionTemplate.selectOne("getLikeDiary", vo);
		}

		@Override
		public void insertDiaryLike(DiaryHeartVo vo) {
			// TODO Auto-generated method stub
			sqlSessionTemplate.insert("createLikeDiary", vo);
			
		}

		@Override
		public void deleteDiaryLike(DiaryHeartVo vo) {
			// TODO Auto-generated method stub
			sqlSessionTemplate.delete("deleteLikeDiary", vo);
			
		}

		@Override
		public void updateDiaryLike(Long diarySeq) {
			// TODO Auto-generated method stub
			sqlSessionTemplate.update("updateLikeDiary", diarySeq);
			
		}

		



}
