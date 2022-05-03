package com.group.exam.calendar.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.exam.board.vo.BoardVo;
import com.group.exam.calendar.dao.CalendarDao;
import com.group.exam.calendar.vo.CalendarBoardVo;
import com.group.exam.calendar.vo.CalendarVo;
import com.group.exam.question.vo.QuestionVo;
import com.group.exam.utils.Criteria;

@Service
public class CalendarServiceImpl implements CalendarService{
	
	private CalendarDao calendarDao;
	
	@Autowired(required = false)
	public CalendarServiceImpl(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}
	
	public ArrayList<CalendarVo> calendarList(){
		return calendarDao.calendarList();
	}

	@Override
	public List<CalendarBoardVo> boardByDate(Criteria cri, String date) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		map.put("date", date);

		return calendarDao.boardByDate(map);
	}

	@Override
	public int boardByDateTotal(String date) {
		// TODO Auto-generated method stub
		return calendarDao.boardByDateTotal(date);
	}

	@Override
	public void insertCalendar(Long currentSeq) {
		calendarDao.insertCalendar(currentSeq);
	}

}
