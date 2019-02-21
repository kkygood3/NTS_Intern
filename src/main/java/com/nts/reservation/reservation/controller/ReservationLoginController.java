/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationLoginController {

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String moveLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(name = "reservationEmail", required = true) String reservationEmail,
		HttpSession session,
		ModelMap model) {

		session.setAttribute("email", reservationEmail);

		return "redirect:/list";
	}
}
