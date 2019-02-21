/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.intercepter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
public class AddCommentViewInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	ReservationService reservationService;

	private Pattern reservationIdPattern = Pattern.compile("(?<=\\/addComment\\/)\\d*");

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws IOException {

		String path = request.getServletPath();
		Matcher matcher = reservationIdPattern.matcher(path);
		int beginIndex = 0;
		int endIndex = 0;

		if (matcher.find()) {
			beginIndex = matcher.start();
			endIndex = matcher.end();
		}
		Long reservationId = Long.parseLong(path.substring(beginIndex, endIndex));
		String userEmail = (String)request.getSession().getAttribute("userEmail");

		if (!reservationService.findFinishReservation(reservationId, userEmail)) {
			response.sendRedirect("/reservation-service");
			String requestUri = request.getRequestURI();
			return false;
		}

		return true;
	}
}
