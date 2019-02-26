/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.interceptor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * reqeust 요청시 사용자의 접속 console log로 기록 설정
 * @author 임상현, life4lord93@nts-corp.com
 */
public class RequestLoggingInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.debug(String.format("DATE : %-25s, IP : %-16s, URL : %-60s, METHOD : %s",
			LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
			request.getRemoteAddr(),
			request.getRequestURL(),
			handler.toString()));
		return true;
	}
}
