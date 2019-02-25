/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.api;

import static com.nts.reservation.dto.request.regex.RegexPattern.EMAIL_REGEX;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nts.reservation.exception.InvalidParamException;

/**
 * @author 육성렬
 */
@RestController
@RequestMapping(path = "/api")
public class AuthApiController {

	private final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

	/**
	 * @desc 비회원 로그인(이메일로 로그인)에 대한 처리
	 * @param userEmail
	 * @param session
	 * @param uriBuilder
	 * @return
	 * @throws InvalidParamException
	 */
	@GetMapping("/login")
	public ResponseEntity<Map<String, String>> loginWithEmail(@RequestParam String userEmail,
		HttpSession session, UriComponentsBuilder uriBuilder) throws InvalidParamException {

		if (!emailPattern.matcher(userEmail).find()) {
			throw new InvalidParamException("userEmail", userEmail);
		}

		session.removeAttribute("userEmail");
		session.setAttribute("userEmail", userEmail);

		URI redirectUri = uriBuilder.path("/myReservation")
			.build()
			.toUri();

		return new ResponseEntity<Map<String, String>>(Collections.singletonMap("redirectUri", redirectUri.toString()),
			HttpStatus.OK);
	}
}
