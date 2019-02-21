package com.nts.reservation.controller.viewController;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.common.DisplayInfo;
import com.nts.reservation.dto.reservation.ReservationInfo;
import com.nts.reservation.service.CommentService;
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
	private CommentService commentService;
	@Autowired
	private HttpSession session;

	@GetMapping(path = "/myreservation/reviewWrite/{reservationId}")
	public String list(@PathVariable(name = "reservationId", required = true) Long reservationId,
		ModelMap model) {
		String email = (String)session.getAttribute("email");
		if (email == null) {
			return "reviewWrite";
		} else if (commentService.checkExistingComment(reservationId) > 0) {
			model.addAttribute("msg", "이미 글을 남기셨습니다.");
			model.addAttribute("url", "/reservation/myreservation");
			return "error/redirect";
		}
		ReservationInfo reservationInfo;
		DisplayInfo displayInfo;
		try {
			reservationInfo = reservationService
				.getReservationByEmailAndRsvId(email, reservationId);
			displayInfo = detailService.getDisplayInfo(reservationInfo.getDisplayInfoId());
			model.addAttribute("productDescription", displayInfo.getProductDescription());
			model.addAttribute("productId", reservationInfo.getProductId());
			model.addAttribute("reservationInfoId", reservationInfo.getId());
			return "reviewWrite";
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("msg", "예약하시지 않은 항목입니다");
			model.addAttribute("url", "/reservation/myreservation");
			return "error/redirect";
		}
	}
}
