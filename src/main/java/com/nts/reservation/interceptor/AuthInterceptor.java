/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.interceptor;

import static com.nts.reservation.constant.RegularExpression.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nts.reservation.util.RegexValidator;

/**
 * 로그인 인증 검사 인터셉터
 * @author jinwoo.bae
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

	/**
	 * 세션에 예매자 이메일이 없거나 이메일이 정규식을 통과하지않는다면
	 * 로그인 성공후 다시 리다이렉트할 url과 에러메시지값을 지정하고 로그인페이지로 이동시킵니다.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession session = request.getSession();
		String reservationEmail = (String)session.getAttribute("reservationEmail");

		if (reservationEmail == null || !RegexValidator.isValid(EMAIL_REGEXP, reservationEmail)) {
			String redirectUrl = request.getRequestURI().substring(request.getContextPath().length());
			FlashMap flashMap = new FlashMap();
			flashMap.put("isError", true);
			flashMap.put("errorMsg", "로그인이 필요합니다.");
			flashMap.put("redirectUrl", redirectUrl);
			response.sendRedirect("/bookinglogin");
			return false;
		}

		return true;
	}
}
