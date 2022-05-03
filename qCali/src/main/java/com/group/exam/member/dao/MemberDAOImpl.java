package com.group.exam.member.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group.exam.member.command.InsertCommand;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.command.MemberInfoCommand;
import com.group.exam.member.vo.MemberVo;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public MemberDAOImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public LoginCommand login(String memberId) {
		// TODO Auto-generated method stub
		return  sqlSessionTemplate.selectOne("memberlogin", memberId);

	}

	@Override
	public LoginCommand findPwd(String memberId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("findPwd", memberId);
	}

	@Override
	public int updateTmpPwd(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("updateTmpPwd", map);
	}

	@Override
	public void insert(InsertCommand command) {
		sqlSessionTemplate.insert("memberInsert", command);

	}
	@Override
	public int nicknameDup(String memberNickname) {
		int res = sqlSessionTemplate.selectOne("nicknameDup", memberNickname);
		return res;
	}

	@Override
	public int idDup(String memberId) {
		return sqlSessionTemplate.selectOne("idDup", memberId);

	}

	@Override
	public void updateAuthkey(MemberVo memberVo) {
		sqlSessionTemplate.update("updateAuthkey", memberVo);
	}

	@Override
	public void updateAuth(MemberVo memberVo) {
		sqlSessionTemplate.update("updateAuth", memberVo);
	}

	@Override
	public int updateMemberPwd(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("updateMemberPwd", map);
	}

	@Override
	public int updateMemberNickname(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("updateMemberNickname", map);
	}

	@Override
	public int deleteMember(Long memberSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("deleteMember", memberSeq);
	}

	@Override
	public int memberQuestionAdd(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("memberQuestionAdd", map);
	}

	@Override
	public void updateApiStatus(HashMap<String, Object> map) {
		sqlSessionTemplate.update("updateApiStatus", map);

	}

	@Override
	public MemberInfoCommand memberInfo(Long memberSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("memberInfo", memberSeq);
	}

}
