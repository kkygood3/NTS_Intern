/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.exception.InvalidFormatException;
import com.nts.util.CheckFormat;

import static com.nts.util.TimeNamingUtils.MINUTES;
import static com.nts.util.TimeNamingUtils.SECONDS;


@RequestMapping("/api/login")
@RestController
public class LoginCheckController {

	@GetMapping
	public boolean validateEmail(@RequestParam(required = true) String reservationEmail, HttpSession httpSession)
		throws InvalidFormatException {

		if (CheckFormat.validateEmailFormat(reservationEmail)) {
			
			httpSession.setAttribute("reservationEmail", reservationEmail);
			httpSession.setMaxInactiveInterval(MINUTES * SECONDS);
			
			return true;
		} else {
			throw new InvalidFormatException("reservationEmail = " + reservationEmail);
		}
	}
}
