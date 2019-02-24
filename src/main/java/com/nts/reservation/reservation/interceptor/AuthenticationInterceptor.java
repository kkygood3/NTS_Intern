package com.nts.reservation.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 로그인이  필요한 Controller에 접근하기 전에 로그인 여부를 확인
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		Object loginEmail = session.getAttribute("email");

		// 로그인이 되어있지 않다면 login 폼으로 이동
		if (loginEmail == null) {
			response.sendRedirect("/loginPage");
			return false;
		}
		
		return true;
	}
	
}
