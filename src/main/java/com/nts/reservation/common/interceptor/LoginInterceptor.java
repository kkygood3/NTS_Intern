package com.nts.reservation.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		String sessionEmail = (String)request.getSession().getAttribute("email");
		if (StringUtils.isEmpty(sessionEmail)) {
			LOGGER.debug("접속 시도 session email : \t{}", sessionEmail);
			response.sendRedirect("/login");
			return false;
		}
		return true;
	}
}
