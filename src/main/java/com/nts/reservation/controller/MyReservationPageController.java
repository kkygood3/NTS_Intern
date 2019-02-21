/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;

import java.net.ConnectException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.MyReservationPageRequestDto;
import com.nts.reservation.exception.UnauthorizedRequestException;
import com.nts.reservation.service.RequestHtmlService;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */

@Controller
public class MyReservationPageController {

	private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private RequestHtmlService requestHtmlService;

	/**
	 * React 서버에 접속하여 서버사이드렌더링된 html 을 받아와서 클라이언트에 그려줌.
	 * @param userEmail
	 * @param session
	 * @return
	 * @throws ConnectException, HttpClientErrorException 
	 */
	@GetMapping(path = "/myReservationWithReact", produces = "text/html; charset=utf8")
	public @ResponseBody String myReservationPageTest(
		HttpSession session, HttpServletRequest request)
		throws UnauthorizedRequestException, ConnectException, HttpClientErrorException {

		String userEmail = (String)session.getAttribute("userEmail");
		if (userEmail == null) {
			String ipAddress = request.getRemoteAddr();
			throw new UnauthorizedRequestException(ipAddress, "/myReservationWithReact");
		}

		List<ReservationInfoDto> list = reservationService.getReservationList(userEmail);
		int count = reservationService.getReservationCount(userEmail);

		MyReservationPageRequestDto requestDto = new MyReservationPageRequestDto(userEmail, list, count);
		String html = "";
		try {
			html = requestHtmlService.requestToReactHtml("/myReservation", requestDto);
		} catch (HttpClientErrorException | ConnectException exception) {
			if (exception instanceof HttpClientErrorException) {
				throw (HttpClientErrorException)exception;
			} else {
				throw (ConnectException)exception;
			}
		}
		return html;
	}
}
