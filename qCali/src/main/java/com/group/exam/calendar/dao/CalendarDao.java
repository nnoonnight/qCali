package com.group.exam.calendar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.group.exam.board.vo.BoardVo;
import com.group.exam.calendar.vo.CalendarBoardVo;
import com.group.exam.calendar.vo.CalendarVo;

public interface CalendarDao {
	public ArrayList<CalendarVo> calendarList();
	public List<CalendarBoardVo> boardByDate(HashMap<String, Object> map);
	public int boardByDateTotal(String date);
	//board추가 시 calendar에 insert
	public void insertCalendar(Long currentSeq);
}
