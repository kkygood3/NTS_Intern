/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
* @author  : 이승수
*/
public class LogInInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("userEmail") == null) {
			response.sendRedirect("/reservation/login");
			return false;
		}
		return true;
	}
}