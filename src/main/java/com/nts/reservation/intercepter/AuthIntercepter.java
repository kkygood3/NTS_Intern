/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.intercepter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 육성렬
 */
public class AuthIntercepter extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws IOException {

		if (request.getSession().getAttribute("userEmail") == null) {
			response.sendRedirect("/reservation-service/bookingLogin");
			String ipAddress = request.getHeader("X-FORWARDED-FOR");
			String requestUri = request.getRequestURI();
			return false;
		}
		return true;
	}
}
