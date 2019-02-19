/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;

import java.net.ConnectException;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.MyReservationPageRequestDto;
import com.nts.reservation.exception.BadRequestException;
import com.nts.reservation.exception.InternalServerErrorException;
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
	 * @throws BadRequestException
	 * @throws InternalServerErrorException
	 */
	@GetMapping(path = "/myReservationWithReact", produces = "text/html; charset=utf8")
	public @ResponseBody String myReservationPageTest(
		@RequestParam(name = "userEmail", required = true) String userEmail,
		HttpSession session) throws BadRequestException, InternalServerErrorException {

		if (!emailPattern.matcher(userEmail).find()) {
			throw new BadRequestException();
		}

		session.removeAttribute("userEmail");
		session.setAttribute("userEmail", userEmail);

		List<ReservationInfoDto> list = reservationService.getReservationList(userEmail);
		int count = reservationService.getReservationCount(userEmail);

		MyReservationPageRequestDto requestDto = new MyReservationPageRequestDto(userEmail, list, count);
		String html = "";
		try {
			html = requestHtmlService.requestMyReservationHtml("/myReservation", requestDto);
		} catch (HttpClientErrorException | ConnectException exception) {
			exception.printStackTrace();
			throw new InternalServerErrorException();
		}
		return html;
	}
}
