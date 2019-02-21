/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 로그인 인증 처리 인터셉터
 * @author jinwoo.bae
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession session = request.getSession();
		String reservationEmail = (String)session.getAttribute("reservationEmail");

		if (reservationEmail == null) {
			String redirectUrl = request.getRequestURI().substring(request.getContextPath().length());
			request.setAttribute("isError", true);
			request.setAttribute("errorMsg", "로그인이 필요합니다.");
			request.setAttribute("redirectUrl", redirectUrl);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookinglogin");
			dispatcher.forward(request, response);
			return false;
		}

		return true;
	}
}
