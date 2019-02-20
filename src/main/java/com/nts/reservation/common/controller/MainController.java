/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

	@GetMapping(path = "/main")
	public String goMain() {
		return "mainpage";
	}

	@GetMapping(path = "/login")
	public String goLogin(HttpSession session) {
		if (session.getAttribute("email") != null) {
			return "redirect:/myreservation";
		}
		return "bookinglogin";
	}

	@PostMapping(path = "/login")
	public String login(@RequestParam(name = "reservation_email") String email, HttpSession session) {

		// TODO email validation check

		session.setAttribute("email", email);
		return "redirect:/myreservation";
	}

	@GetMapping(path = "/logout")
	public String logoutTest(HttpSession session) {
		session.removeAttribute("email");
		return "redirect:/";
	}

	@GetMapping(path = "/error")
	public String goError() {
		return "common/error";
	}
}
