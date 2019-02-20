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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.dto.common.DisplayInfo;
import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.service.DetailService;
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
	private DetailService detailService;
	@Autowired
	private HttpSession session;

	@GetMapping(path = "/myreservation/reviewWrite/{reservationId}")
	public String list(@PathVariable(name = "reservationId", required = true) Long reservationId,
		ModelMap model) {
		// if there is no email in session || if there is no corresponding reservation with email
		if (session.getAttribute("email") == null) {
			try {
				return "redirect:/myreservation";
			} catch (Exception e) {
				return "forward:/myreservation";
			}
		}
		try {
			ReservationInfo reservationInfo = reservationService
				.getReservationByEmailAndRsvId((String)session.getAttribute("email"), reservationId);
			DisplayInfo displayInfo = detailService.getDisplayInfo(reservationInfo.getDisplayInfoId());
			ObjectMapper mapper = new ObjectMapper();
			String reservationInfoStr = mapper.writeValueAsString(reservationInfo);

			model.addAttribute("productDescription", displayInfo.getProductDescription());
			model.addAttribute("reservationInfo", reservationInfoStr);

			return "reviewWrite";
		} catch (Exception e) {
			try {
				return "redirect:/myreservation";
			} catch (Exception ex) {
				return "forward:/myreservation";
			}
		}

	}
}
