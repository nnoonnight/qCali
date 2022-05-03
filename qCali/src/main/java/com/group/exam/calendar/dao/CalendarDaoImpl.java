package com.group.exam.calendar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.group.exam.board.vo.BoardVo;
import com.group.exam.calendar.vo.CalendarBoardVo;
import com.group.exam.calendar.vo.CalendarVo;
import com.group.exam.question.vo.QuestionVo;

@Repository
public class CalendarDaoImpl implements CalendarDao {

	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	public CalendarDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public ArrayList<CalendarVo> calendarList() {

		List<CalendarVo> calendar = null;
		calendar = sqlSessionTemplate.selectList("calendarList");
		return (ArrayList<CalendarVo>) calendar;
	}

	@Override
	public List<CalendarBoardVo> boardByDate(HashMap<String, Object> map) {
		System.out.println(map);
		return sqlSessionTemplate.selectList("boardByDate", map);
	}

	@Override
	public int boardByDateTotal(String date) {
		return sqlSessionTemplate.selectOne("boardByDateTotal", date);
	}

	@Override
	public void insertCalendar(Long currentSeq) {
		sqlSessionTemplate.insert("insertCalendar", currentSeq);
	}

}
