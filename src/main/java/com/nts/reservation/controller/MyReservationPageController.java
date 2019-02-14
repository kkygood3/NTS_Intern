/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.exception.BadRequestException;

/**
 * @author 육성렬
 */

@Controller
public class MyReservationPageController {

	private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

	/** 
	 * @desc 이메일에 해당하는 예약 확인 목록 조회 페이지 
	 * @param userEmail
	 * @param session
	 * @return
	 * @throws BadRequestException
	 */
	@GetMapping("/myReservation")
	public String myReservationPage(
		@RequestParam(name = "userEmail", required = true) String userEmail,
		HttpSession session) throws BadRequestException {

		if (!emailPattern.matcher(userEmail).find()) {
			throw new BadRequestException();
		}

		session.removeAttribute("userEmail");
		session.setAttribute("userEmail", userEmail);
		return "myReservationPage";
	}
}
