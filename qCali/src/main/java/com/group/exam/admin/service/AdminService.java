package com.group.exam.admin.service;

import java.util.List;
import java.util.Map;

import com.group.exam.admin.command.AdminAuthInfoCommand;
import com.group.exam.admin.command.AdminBoardCommand;
import com.group.exam.admin.command.AdminQuestionMember;
import com.group.exam.member.vo.MemberVo;
import com.group.exam.question.vo.QuestionRegistCommand;
import com.group.exam.question.vo.QuestionVo;
import com.group.exam.utils.Criteria;

public interface AdminService {
	public List<AdminQuestionMember> questionList(Criteria cri);
	public List<AdminQuestionMember> questionAllList(Criteria cri);
	public List<MemberVo> selectMember(Criteria cri);
	public List<AdminBoardCommand> boardList(Criteria cri);
		
	public AdminAuthInfoCommand authenticate(String adminId, String adminPassword);
	public void memberDelete(Long memberSeq);

	public void questionApprove(Long questionSeq);
	public void addQuestion(QuestionRegistCommand qusestionRegistCommand);
	public void questionDelete(Long questionSeq);

	public AdminBoardCommand selectBybseq(Long boardSeq);
	public void boardDelete(Long boardSeq);

	public int boardListTotal();
	public int countQuestionList();
	public int memberListTotal();
	public int questionListTotal();
	
}
