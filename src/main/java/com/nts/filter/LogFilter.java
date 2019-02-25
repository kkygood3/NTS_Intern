/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		long beginTime = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		String requestUri = httpRequest.getRequestURI();
		
		if(requestUri.startsWith("/static") || requestUri.startsWith("/img")) {
			return;
		}
		
		long endTime = System.currentTimeMillis();
		long proceedTime = endTime - beginTime;
		
		String clientIp = request.getRemoteAddr();
		clientIp = String.format("%-20s", clientIp);
		
		logger.info("ClientIP : {} , URL	 : {},  Method : {}, ProceedTime : {}ms",clientIp, String.format("%-30s", requestUri),String.format("%-6s", httpRequest.getMethod()), proceedTime);
	}

	@Override
	public void destroy() {
		
	}
}
