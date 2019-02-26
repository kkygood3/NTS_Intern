/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 육성렬
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	/**
	 * @desc 인증 페이지에 접근 시 인증되지 않은 자는 인증 하는 페이지로 리다이렉트
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws IOException {

		if (request.getSession().getAttribute("userEmail") == null) {
			response.sendRedirect("/bookingLogin");
			return false;
		}
		return true;
	}
}
