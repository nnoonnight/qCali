package com.group.exam.calendar.service;

import java.util.ArrayList;
import java.util.List;

import com.group.exam.board.vo.BoardVo;
import com.group.exam.calendar.vo.CalendarBoardVo;
import com.group.exam.calendar.vo.CalendarVo;
import com.group.exam.utils.Criteria;

public interface CalendarService {
	public ArrayList<CalendarVo> calendarList();
	public List<CalendarBoardVo> boardByDate(Criteria cri, String date);
	public int boardByDateTotal(String date);
	//board추가 시 calendar에 insert
	public void insertCalendar(Long currentSeq);
}
