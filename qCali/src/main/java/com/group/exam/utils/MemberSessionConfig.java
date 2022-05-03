package com.group.exam.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.group.exam.member.command.LoginCommand;

@WebListener
public class MemberSessionConfig implements HttpSessionListener {
	//session map 을 이용하여 키값으로 세션의 고유 생성 아이디값, 값으로는 HttpSession을 갖는다.
	//로그인 성공시 세션이 만들어지게 되면 어노테이션 WebListener에 의해서 세션 값이 sessions에 저장이 되고,
	//로그아웃이나 브라우저를 종료하여 세션이 사라지게 되면 Destroyed에 의해서 제거 된다.
	//getSEssionidCheck 메소드를 통해서 세션의 값과 비교할 로그인 아이디 값을 받도록 만들어 보았다.
	//synchronidez를 통해서 순차적인 처리를 하도록 하였다.
	//
	

	private static final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

	// 중복 로그인 지우기
	public synchronized static String getSessionidCheck(String type, String compareId) {
		String result = "";
		for (String key : sessions.keySet()) {
			HttpSession hs = sessions.get(key);
			LoginCommand auth = new LoginCommand();

			if (hs != null) {
				auth = (LoginCommand) hs.getAttribute(type);
				if (auth != null && auth.getMemberId().toString().equals(compareId)) {
					result = key.toString();
				}
			}
		}
		removeSessionForDoubleLogin(result);
		return result;
	}

	private static void removeSessionForDoubleLogin(String userId) {
		System.out.println("remove userId : " + userId);
		if (userId != null && userId.length() > 0) {
			sessions.get(userId).invalidate();
			sessions.remove(userId);
		}
	}

	@Override
	public void sessionCreated(HttpSessionEvent hse) {
		System.out.println(hse);
		sessions.put(hse.getSession().getId(), hse.getSession());

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent hse) {
		if (sessions.get(hse.getSession().getId()) != null) {
			sessions.get(hse.getSession().getId()).invalidate();
			sessions.remove(hse.getSession().getId());
		}

	}

}