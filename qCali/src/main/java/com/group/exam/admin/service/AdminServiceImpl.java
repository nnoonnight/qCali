package com.group.exam.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.exam.admin.command.AdminAuthInfoCommand;
import com.group.exam.admin.command.AdminBoardCommand;
import com.group.exam.admin.command.AdminQuestionMember;
import com.group.exam.admin.dao.AdminDao;
import com.group.exam.admin.exception.IdpasswordNotMatchingException;
import com.group.exam.admin.vo.AdminVo;
import com.group.exam.member.vo.MemberVo;
import com.group.exam.question.vo.QuestionRegistCommand;
import com.group.exam.question.vo.QuestionVo;
import com.group.exam.utils.Criteria;

@Service
public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	@Autowired
	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	// 관리자 로그인 정보
	@Override
	public AdminAuthInfoCommand authenticate(String aId, String aPassword) {
		AdminVo adminVo = (AdminVo) adminDao.selectByaId(aId);
		if (adminVo == null) {
			throw new IdpasswordNotMatchingException();
		}
		if (!adminVo.matchPassword(aPassword)) {
			throw new IdpasswordNotMatchingException();
		}
		// TODO Auto-generated method stub
		return new AdminAuthInfoCommand(adminVo.getAdminId(), adminVo.getAdminNickname(), adminVo.getAdminSeq());
	}

	// 질문 승인

	// 멤버 삭제
	@Override
	public void memberDelete(Long memberSeq) {
		adminDao.memberDelete(memberSeq);
	}

	// 상태가 'F'인 질문들 출력
	@Override
	public List<AdminQuestionMember> questionList(Criteria cri) {
		return adminDao.questionList(cri);
	}

	// 질문 승인
	@Override
	public void questionApprove(Long questionSeq) {
		adminDao.questionApprove(questionSeq);
		;

	}

	// 질문 거부(삭제)
	@Override
	public void questionDelete(Long questionSeq) {
		adminDao.questionDelete(questionSeq);
	}

	// 질문 추가
	@Override
	public void addQuestion(QuestionRegistCommand questionRegistCommand) {
		QuestionVo questionVo = new QuestionVo(questionRegistCommand.getQuestionContent());

		System.out.println(questionVo);
		adminDao.questionAdd(questionVo);
	}

	@Override
	public List<AdminQuestionMember> questionAllList(Criteria cri) {
		return adminDao.questionAllList(cri);
	}

	// 멤버 리스트
	@Override
	public List<MemberVo> selectMember(Criteria cri) {
		// TODO Auto-generated method stub
		return adminDao.selectMember(cri);
	}

	@Override
	public List<AdminBoardCommand> boardList(Criteria cri) {
		System.out.println(cri);
		// TODO Auto-generated method stub
		return adminDao.boardList(cri);
	}

	@Override
	public AdminBoardCommand selectBybseq(Long boardSeq) {
		// TODO Auto-generated method stub
		return adminDao.selectBybseq(boardSeq);
	}

	@Override
	public void boardDelete(Long boardSeq) {
		adminDao.boardDelete(boardSeq);
	}

	@Override
	public int boardListTotal() {

		return adminDao.countBoardList();
	}

	@Override
	public int countQuestionList() {
		// TODO Auto-generated method stub
		return adminDao.countQuestionList();
	}

	@Override
	public int memberListTotal() {
		// TODO Auto-generated method stub
		return adminDao.memberListTotal();
	}

	@Override
	public int questionListTotal() {
		// TODO Auto-generated method stub
		return adminDao.questionListTotal();
	}

}
