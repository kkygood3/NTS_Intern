package com.nts.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 컨트롤러 요청마다 비회원 로그인 key값인 email을
 * 세션에서 조회하는 인터셉터
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		request.setAttribute("email", request.getSession().getAttribute("email"));
	}
}
