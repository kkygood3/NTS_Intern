/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nts.reservation.common.annotation.MustLogin;
import com.nts.reservation.common.exception.UnauthenticateException;

/**
 * login 확인 interceptor, use with MustLogin annotation 
 * @author 임상현, life4lord93@nts-corp.com
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {

		if (isNotMethod(handler)) {
			return true;
		}

		HandlerMethod handlerMethod = (HandlerMethod)handler;
		MustLogin mustLogin = handlerMethod.getMethodAnnotation(MustLogin.class);

		if (isNull(mustLogin)) {
			return true;
		}

		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("email");

		if (StringUtils.isBlank(email)) {
			if (isApi(request.getRequestURI())) {
				throw new UnauthenticateException();
			} else {
				response.sendRedirect("/login");
			}
			return false;
		}

		return true;
	}

	private boolean isApi(String uri) {
		return uri.contains("/api");
	}

	private boolean isNotMethod(Object handler) {
		return !(handler instanceof HandlerMethod);
	}

	private boolean isNull(Object object) {
		return object == null;
	}

}
