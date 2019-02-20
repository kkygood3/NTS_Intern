package com.nts.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SessionInterceptor extends HandlerInterceptorAdapter{
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		HttpSession session = request.getSession();
		
		String email = (String)session.getAttribute("email");
		if (email != null) {
			request.setAttribute("email", email);
		}
	}
}
