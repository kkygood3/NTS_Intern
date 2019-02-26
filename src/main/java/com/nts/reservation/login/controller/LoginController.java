/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.login.controller;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.nts.reservation.common.regexp.Regexp;

@Controller
@Validated
public class LoginController {

	/**
	 * login page 호출
	 */
	@GetMapping(value = {"/login"})
	public String login() {
		return "login";
	}

	/**
	 * email 정보 session에 저장(로그인)
	 */
	@PostMapping(value = {"/login"})
	public String login(HttpSession httpSession,
		@Pattern(regexp = Regexp.EMAIL, message = "email is not vaild") String email) {

		httpSession.setAttribute("email", email);
		return "redirect:/my-reservation";
	}

	/**
	 * email 정보 session 초기화(로그아웃)
	 */
	@GetMapping(value = {"/logout"})
	public String logout(HttpSession httpSession) {
		httpSession.setAttribute("email", null);
		return "redirect:/main";
	}
}
