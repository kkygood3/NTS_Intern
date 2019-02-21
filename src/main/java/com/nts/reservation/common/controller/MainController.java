/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.common.utils.RegexPattern;

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
	public ModelAndView login(
		@RequestParam(name = "reservation_email") String email, HttpSession session, ModelAndView modelAndView) {
		
		modelAndView.setViewName("redirect:/myreservation");

		String sessionEmail = (String)session.getAttribute("email");
		if (sessionEmail != null) {
			return modelAndView;
		}

		if (checkValidation(email) == false) {
			modelAndView.setViewName("redirect:/login");
			modelAndView.addObject("errorMessage", "email 형식이 올바르지 않습니다.");
			return modelAndView;
		}

		session.setAttribute("email", email);
		return modelAndView;
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

	private boolean checkValidation(String email) {
		if (email == null) {
			return false;
		}
		if (email.length() == 0) {
			return false;
		}
		if (!Pattern.matches(RegexPattern.EMAIL_REGEX, email)) {
			return false;
		}
		return true;
	}
}
