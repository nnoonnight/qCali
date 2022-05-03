package com.group.exam.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.exam.board.command.BoardlistCommand;
import com.group.exam.board.command.QuestionAdayCommand;
import com.group.exam.board.dao.BoardDao;
import com.group.exam.board.vo.BoardHeartVo;
import com.group.exam.board.vo.BoardVo;
import com.group.exam.board.vo.NoticeAdminVo;
import com.group.exam.board.vo.ReplyVo;
import com.group.exam.utils.Criteria;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	public BoardServiceImpl() {

	}

	@Override
	public void insertBoard(BoardVo boardVo) {
		// TODO Auto-generated method stub

		boardDao.insertBoard(boardVo);

	}

	@Override
	public List<BoardlistCommand> boardList(Criteria cri) {
		// TODO Auto-generated method stub

		return boardDao.boardList(cri);
	}

	@Override
	public List<BoardlistCommand> boardMyList(Criteria cri, Long memberSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("memberSeq", memberSeq);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		return boardDao.boardMyList(map);
	}

	@Override
	public BoardlistCommand boardListDetail(Long boardSeq) {
		// TODO Auto-generated method stub
		return boardDao.boardListDetail(boardSeq);
	}

	@Override
	public void deleteBoardOne(Long boardSeq, Long memberSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Long> map = new HashMap<String, Long>();

		map.put("boardSeq", boardSeq);
		map.put("memberSeq", memberSeq);
		boardDao.deleteBoardOne(map);

	}

	// 게시물 조횟수 up
	@Override
	public void boardCountup(Long boardSeq) {
		// TODO Auto-generated method stub

		boardDao.boardCountup(boardSeq);

	}

	@Override
	public int listCount() {
		// TODO Auto-generated method stub
		return boardDao.listCount();
	}

	@Override
	public void updateBoard(String boardTitle, String boardContent, Long boardSeq) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("boardTitle", boardTitle);
		map.put("boardContent", boardContent);
		map.put("boardSeq", boardSeq);

		boardDao.updateBoard(map);

	}

	@Override
	public int mylistCount(Long memberSeq) {
		// TODO Auto-generated method stub
		return boardDao.boardMylistCount(memberSeq);
	}

	@Override
	public void insertBoardLike(BoardHeartVo vo) {
		// TODO Auto-generated method stub
		boardDao.insertBoardLike(vo);
		boardDao.updateBoardLike(vo.getBoardSeq());

	}

	@Override
	public void deleteBoardLike(BoardHeartVo vo) {
		// TODO Auto-generated method stub
		boardDao.deleteBoardLike(vo);
		boardDao.updateBoardLike(vo.getBoardSeq());

	}

	@Override
	public int getBoardLike(BoardHeartVo vo) {
		// TODO Auto-generated method stub
		return boardDao.getBoardLike(vo);
	}

	@Override
	public String memberAuth(Long memberSeq) {
		// TODO Auto-generated method stub
		return boardDao.memberAuth(memberSeq);
	}

	@Override
	public int memberLevelup(Long memberSeq, int mytotal, int memberLevel) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("memberSeq", memberSeq);
		map.put("mytotal", mytotal);
		map.put("memberLevel", memberLevel);

		return boardDao.memberLevelup(map);

	}

	@Override
	public QuestionAdayCommand questionselect(int num) {
		// TODO Auto-generated method stub
		return boardDao.questionselect(num);
	}

	@Override
	public int getSequence() {
		// TODO Auto-generated method stub
		return boardDao.getSequence();
	}

	@Override
	public int currentSequence() {
		// TODO Auto-generated method stub
		return boardDao.currentSequence();
	}

	@Override
	public List<BoardlistCommand> boardSearch(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDao.boardSearch(map);
	}

	@Override
	public List<NoticeAdminVo> noticelist() {
		// TODO Auto-generated method stub
		return boardDao.noticelist();
	}

	@Override
	public Long currentBoardSeq() {
		// TODO Auto-generated method stub
		return boardDao.currentBoardSeq();
	}

	@Override
	public int boardSearchCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDao.boardSearchCount(map);
	}

	// 댓글 기능 관련
	@Override
	public List<ReplyVo> replyList(Long boardSeq) {
		return boardDao.replyList(boardSeq);
	}

	@Override
	public void replyInsert(ReplyVo replyVo) {
		boardDao.replyInsert(replyVo);
	}

	@Override
	public void replyUpdate(ReplyVo replyVo) {
		boardDao.replyUpdate(replyVo);
	}

	@Override
	public void replyDelete(ReplyVo replyVo) {
		boardDao.replyDelete(replyVo);
	}

	@Override
	public int replyCount(Long boardSeq) {
		return boardDao.replyCount(boardSeq);

	}

	@Override
	public List<BoardlistCommand> boardListTodayArticle(Criteria cri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int boardTodayCount() {
		// TODO Auto-generated method stub
		return boardDao.boardTodayCount();
	}
}
