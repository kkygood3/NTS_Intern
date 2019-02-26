/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 육성렬
 */
public class RequestLogInterceptor extends HandlerInterceptorAdapter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * @desc 요청에 대한 로그 남김.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String ipAddress = request.getRemoteAddr();
		String requestUri = request.getRequestURI();
		String handlerName = handler.toString();

		logger.debug(" URL path : {}, client Ip : {}, 호출한 Handler : {}", requestUri, ipAddress,
			handlerName);
		return true;
	}

}
