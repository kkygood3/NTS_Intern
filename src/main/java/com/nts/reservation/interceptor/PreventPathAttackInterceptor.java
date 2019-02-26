/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.interceptor;

import static com.nts.reservation.controller.advice.message.ErrorMessage.BAD_REQUEST_ERROR_MSG;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 육성렬
 */
public class PreventPathAttackInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @desc 파일 다운로드할 때 이미지 경로에 대한 공격 방어.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String query = request.getQueryString();
		if (query == null) {
			return true;
		}

		query = query.replaceAll("%2E", ".");
		query = query.replaceAll("%2F", "/");
		query = query.replaceAll("%5C", "/");

		if (query.contains("../") || query.contains("./")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, BAD_REQUEST_ERROR_MSG);
			logger.error("파일 경로를 통한 공격 감지. [공격자 ip] : {}, [공격 패턴] : {}", request.getRemoteAddr(),
				request.getQueryString());
			return false;
		}
		return true;
	}
}
