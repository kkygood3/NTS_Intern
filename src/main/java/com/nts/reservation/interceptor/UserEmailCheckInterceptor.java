package com.nts.reservation.interceptor;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public class UserEmailCheckInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private HttpSession session;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		session = request.getSession();
		if (session.getAttribute("email") == null) {
			response.sendRedirect("/reservation/bookinglogin");
		}
		return true;
	}
}
