package com.group.exam.admin.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group.exam.admin.command.AdminBoardCommand;
import com.group.exam.admin.command.AdminQuestionMember;
import com.group.exam.member.vo.MemberVo;
import com.group.exam.question.vo.QuestionVo;
import com.group.exam.utils.Criteria;

@Repository
public class AdminDaoImpl implements AdminDao {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public AdminDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	// 관리자 로그인
	@Override
	public Object selectByaId(String aId) {
		return sqlSessionTemplate.selectOne("selectByaId", aId);
	}

	// 회원 탈퇴
	@Override
	public void memberDelete(Long memberSeq) {
		sqlSessionTemplate.delete("memberDelete", memberSeq);
	}

	@Override
	public List<AdminQuestionMember> questionList(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("questionList", cri);
	}

	@Override
	public void questionApprove(Long questionSeq) {
		sqlSessionTemplate.update("questionApprove", questionSeq);
	}

	@Override
	public void questionDelete(Long questionSeq) {
		sqlSessionTemplate.delete("questionDelete", questionSeq);
	}

	@Override
	public void questionAdd(QuestionVo questionVo) {
		sqlSessionTemplate.insert("questionAdd", questionVo);
	}

	@Override
	public List<AdminQuestionMember> questionAllList(Criteria cri) {
		return sqlSessionTemplate.selectList("questionListAll", cri);
	}

	@Override
	public List<MemberVo> selectMember(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("selectMember", cri);
	}

	@Override
	public List<AdminBoardCommand> boardList(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("adminboardList", cri);
	}

	@Override
	public AdminBoardCommand selectBybseq(Long boardSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("selectBybseq", boardSeq);
	}

	@Override
	public void boardDelete(Long boardSeq) {
		sqlSessionTemplate.delete("boardDelete", boardSeq);
	}

	@Override
	public int countBoardList() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("countBoardList");
	}

	@Override
	public int countQuestionList() {
		return sqlSessionTemplate.selectOne("countQuestionList");
	}

	@Override
	public int memberListTotal() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("memberListTotal");
	}

	@Override
	public int questionListTotal() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("questionListTotal");
	}

}
