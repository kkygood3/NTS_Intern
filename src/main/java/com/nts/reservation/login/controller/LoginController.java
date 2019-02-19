/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.login.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping(value = {"/login"})
	public String login() {
		return "login";
	}

	@PostMapping(value = {"/login"})
	public String login(HttpSession httpSession, String email) {
		httpSession.setAttribute("email", email);
		return "redirect:/my-reservation";
	}

	@GetMapping(value = {"/logout"})
	public String logout(HttpSession httpSession) {
		httpSession.setAttribute("email", null);
		return "redirect:/main";
	}
}
