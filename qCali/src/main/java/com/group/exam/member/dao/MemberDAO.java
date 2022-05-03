package com.group.exam.member.dao;

import java.util.HashMap;

import com.group.exam.member.command.InsertCommand;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.command.MemberInfoCommand;
import com.group.exam.member.vo.MemberVo;

public interface MemberDAO {

	// 로그인 관련
	// 로그인 관련

	public abstract LoginCommand login(String memberId);// 로그인

	public abstract LoginCommand findPwd(String memberId); // 비밀번호 찾기 => 아이디 검색

	public abstract int updateTmpPwd(HashMap<String, Object> map); // 임시 비밀번호 셋팅

	// 회원 가입 관련

	public abstract void insert(InsertCommand command);// 회원가입

	public abstract int nicknameDup(String memberId);// 닉네임 중복확인

	public abstract int idDup(String memberId);// id(email) 중복확인

	public abstract void updateAuthkey(MemberVo memebrVo);// 인증메일 발송후 인증키 저장

	public abstract void updateAuth(MemberVo memebrVo);// 메일 클릭시 인증 완료
	
	public void updateApiStatus(HashMap<String, Object>map); // api 로그인시 
	
	//popup Member정보
	public MemberInfoCommand memberInfo(Long memberSeq);


	// 회원 정보 수정 관련

	public int updateMemberPwd(HashMap<String, Object> map);

	public int updateMemberNickname(HashMap<String, Object> map);

	public int deleteMember(Long memberSeq);

	// 회원 질문 추가

	public int memberQuestionAdd(HashMap<String, Object> map);



}
