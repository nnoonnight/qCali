package com.group.exam.member.service;

import com.group.exam.member.command.InsertCommand;
import com.group.exam.member.command.LoginCommand;
import com.group.exam.member.command.MemberInfoCommand;

public interface MemberService {
	
	public LoginCommand login(String memberId);//로그인
	
	public LoginCommand findPwd(String memberId); //비밀번호 찾기 관련 => 해당 하는 회원 정보 가져오기
	
	public int updateTmpPwd(String tmpPwd, String memberId);
	
	
	public void memberInsert(InsertCommand insertCommand);//회원가입	
	
	public int nicknameDup(String memberNickname);//닉네임 중복확인
	
	public int idDup(String memberId);//id(email) 중복확인
	
	public void updateAuthkey(InsertCommand insertCommand);//인증메일 발송후 인증키 저장
	
	public void updateAuth(String memberAuthkey);//메일 클릭시 인증 완료
	
	public void updateApiStatus(String api, Long memberSeq);
	
	//popup Member정보
	public MemberInfoCommand memberInfo(Long memberSeq);
	
	//회원 정보 수정 관련
	
	public int updateMemberPwd(String memberPassword, Long memberSeq);
	
	public int updateMemberNickname (String memberNickname, Long memberSeq);
	
	public int deleteMember (Long memberSeq);
	
	//회원 질문 추가
	
	public int memberQuestionAdd(String questionContent, Long memberSeq);
	
}

