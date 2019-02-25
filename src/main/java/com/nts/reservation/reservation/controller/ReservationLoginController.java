/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationLoginController {
	private final static Logger LOG = Logger.getGlobal();

	@RequestMapping(value = "/loginPage", method = RequestMethod.GET)
	public String moveLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(name = "reservationEmail", required = true) String reservationEmail,
		HttpSession session, ModelMap model) {

		if (reservationEmail == null) {
			LOG.warning("잘못된 Email입니다. Email : " + reservationEmail);
			throw new IllegalArgumentException();
		}

		session.setAttribute("email", reservationEmail);

		return "redirect:/list";
	}
}
