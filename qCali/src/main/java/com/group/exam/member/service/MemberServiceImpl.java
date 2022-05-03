package com.group.exam.member.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.exam.member.command.InsertCommand;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.command.MemberInfoCommand;
import com.group.exam.member.dao.MemberDAO;
import com.group.exam.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;

	@Override
	public LoginCommand login(String memberId) {
		// TODO Auto-generated method stub

		return memberDAO.login(memberId);

	}

	@Override
	public LoginCommand findPwd(String memberId) {
		// TODO Auto-generated method stub
		return memberDAO.findPwd(memberId);
	}

	@Override
	public int updateTmpPwd(String tmpPwd, String memberId) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("tmpPwd", tmpPwd);
		map.put("memberId", memberId);

		return memberDAO.updateTmpPwd(map);
	}

	@Override
	public void memberInsert(InsertCommand insertCommand) {

		memberDAO.insert(insertCommand);

	}

	@Override
	public int nicknameDup(String memberNickname) {


		int res = memberDAO.nicknameDup(memberNickname);
		return res;
	}

	@Override
	public int idDup(String memberId) {

		return memberDAO.idDup(memberId);
	}

	@Override
	public void updateAuthkey(InsertCommand insertCommand) {
		MemberVo memberVo = new MemberVo();
		memberVo.setMemberId(insertCommand.getMemberId());
		memberVo.setMemberAuthkey(insertCommand.getMemberAuthkey());
		memberDAO.updateAuthkey(memberVo);
	}

	public void updateAuth(String mAuthkey) {
		MemberVo memberVo = new MemberVo();
		memberVo.setMemberAuth("T");
		memberVo.setMemberAuthkey(mAuthkey);
		memberDAO.updateAuth(memberVo);
	}

	@Override
	public int updateMemberPwd(String memberPassword, Long memberSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("memberPassword", memberPassword);
		map.put("memberSeq", memberSeq);
		return memberDAO.updateMemberPwd(map);
	}

	@Override
	public int updateMemberNickname(String memberNickname, Long memberSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("memberNickname", memberNickname);
		map.put("memberSeq", memberSeq);

		return memberDAO.updateMemberNickname(map);
	}

	@Override
	public int deleteMember(Long memberSeq) {
		// TODO Auto-generated method stub
		return memberDAO.deleteMember(memberSeq);
	}

	@Override
	public int memberQuestionAdd(String questionContent, Long memberSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("questionContent", questionContent);
		map.put("memberSeq", memberSeq);
		return memberDAO.memberQuestionAdd(map);
	}


	@Override
	public void updateApiStatus(String api, Long memberSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("api", api);
		map.put("memberSeq", memberSeq);
		memberDAO.updateApiStatus(map);
	}

	@Override
	public MemberInfoCommand memberInfo(Long memberSeq) {
		// TODO Auto-generated method stub
		return memberDAO.memberInfo(memberSeq);
	}


}
