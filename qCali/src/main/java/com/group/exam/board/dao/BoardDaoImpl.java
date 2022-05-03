package com.group.exam.board.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.group.exam.board.command.BoardlistCommand;
import com.group.exam.board.command.QuestionAdayCommand;
import com.group.exam.board.vo.BoardHeartVo;
import com.group.exam.board.vo.BoardVo;
import com.group.exam.board.vo.NoticeAdminVo;
import com.group.exam.board.vo.ReplyVo;
import com.group.exam.utils.Criteria;

@Repository
public class BoardDaoImpl implements BoardDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public BoardDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public void insertBoard(BoardVo boardVo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("insertBoard", boardVo);

	}

	@Override
	public void updateBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		// 게시글 제목과 내용 수정
		sqlSessionTemplate.update("updateBoard", map);

	}

	@Override
	public void deleteBoardOne(HashMap<String, Long> map) {
		// TODO Auto-generated method stub

		sqlSessionTemplate.delete("deleteBoardOne", map);

	}

	@Override
	public List<BoardlistCommand> boardMyList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub

		return sqlSessionTemplate.selectList("boardMylist", map);
	}

	@Override
	public BoardlistCommand boardListDetail(Long boardSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardlistDetail", boardSeq);
	}

	@Override
	public int listCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardlistCount");
	}

	@Override
	public List<BoardlistCommand> boardList(Criteria cri) {
		// TODO Auto-generated method stub

		return sqlSessionTemplate.selectList("boardlist", cri);
	}

	@Override
	public void boardCountup(Long boardSeq) {
		// TODO Auto-generated method stub

		sqlSessionTemplate.update("boardCountup", boardSeq);

	}

	@Override
	public int boardMylistCount(Long memberSeq) {
		// TODO Auto-generated method stub

		return sqlSessionTemplate.selectOne("boardMylistCount", memberSeq);
	}

	// 좋아요 기능 관련
	@Override
	public int getBoardLike(BoardHeartVo vo) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("getLike", vo);
	}

	@Override
	public void insertBoardLike(BoardHeartVo vo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.insert("createLike", vo);

	}

	@Override
	public void deleteBoardLike(BoardHeartVo vo) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.delete("deleteLike", vo);

	}

	@Override
	public void updateBoardLike(Long boardSeq) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update("updateLike", boardSeq);

	}

	@Override
	public String memberAuth(Long memberSeq) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("memberAuth", memberSeq);
	}

	// 멤버 레벨 관련
	@Override
	public int memberLevelup(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("memberLevelup", map);

	}

	@Override
	public QuestionAdayCommand questionselect(int num) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("questionselect", num);
	}

	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("getSequence");
	}

	@Override
	public int currentSequence() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("currentSequence");
	}

	@Override
	public List<BoardlistCommand> boardSearch(HashMap<String, Object> map) {
		// TODO Auto-generated method stub

		System.out.println("dao\n" + map.toString());
		return sqlSessionTemplate.selectList("boardSearch", map);
	}

	@Override
	public List<NoticeAdminVo> noticelist() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("noticeSelectInMain");
	}

	@Override
	public Long currentBoardSeq() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("currentBoardSeq");
	}

	@Override
	public int boardSearchCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardSearchCount", map);
	}

	// 댓글 관련
	@Override
	public List<ReplyVo> replyList(Long boardSeq) {
		return sqlSessionTemplate.selectList("replyList", boardSeq);
		// 게시글에 맞춰서 댓글 리스트 띄우기
	}

	@Override
	public int replyCount(Long boardSeq) {
		return sqlSessionTemplate.selectOne("replyCount", boardSeq);
		// 댓글 갯수 카운트
	}

	@Override
	public void replyInsert(ReplyVo replyVo) {

		sqlSessionTemplate.insert("replyInsert", replyVo);
		// 댓글 쓰기
	}

	@Override
	public void replyUpdate(ReplyVo replyVo) {
		sqlSessionTemplate.update("replyUpdate", replyVo);
		// 댓글 수정
	}

	@Override
	public void replyDelete(ReplyVo replyVo) {
		sqlSessionTemplate.delete("replyDelete", replyVo);
		// 댓글 삭제
	}

	@Override
	public List<BoardlistCommand> boardListTodayArticle(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int boardTodayCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("boardTodayCount");
	}
}
