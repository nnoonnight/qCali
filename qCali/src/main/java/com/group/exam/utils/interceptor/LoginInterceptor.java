//package com.group.exam.utils.interceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//public class LoginInterceptor extends HandlerInterceptorAdapter{
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		HttpSession session = request.getSession();
//		String path = request.getRequestURI();
//		if(path.contains("/main") || path.contains("/login")) {
//			return true;
//		}else if(session.getAttribute("adminAuthInfoCommand") == 
//				null && session.getAttribute("memberLogin") == null) {
//				//session admin로그인하고 memberLogin 둘다 없으면 main으로 redirect하도록
//			response.sendRedirect(request.getContextPath()+"/main");
//		}
//		
//		
//		return true;
//	}
//	
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
//		super.postHandle(request, response, handler, mav);
//	}
//
//}
