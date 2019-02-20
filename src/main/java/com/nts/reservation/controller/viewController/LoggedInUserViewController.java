package com.nts.reservation.controller.viewController;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.service.ReservationService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

@Controller
public class LoggedInUserViewController {
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;

	@GetMapping(path = "/myreservation/reviewWrite/{reservationId}")
	public String list(@PathVariable(name = "reservationId", required = true) Long reservationId,
		ModelMap model) {
		// if there is no email in session || if there is no corresponding reservation with email
		if (session.getAttribute("email") == null) {
			return "redirect:/myreservation";
		}
		try {
			ReservationInfo reservationInfo = reservationService
				.getReservationByEmailAndRsvId((String)session.getAttribute("email"), reservationId);

			model.addAttribute("reservationInfo", reservationInfo);

			return "reviewWrite";
		} catch (Exception e) {
			return "redirect:/myreservation";
		}

	}
}
