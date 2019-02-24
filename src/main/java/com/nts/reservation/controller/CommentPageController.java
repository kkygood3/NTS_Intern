/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.exception.PageNotFoundException;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
@Controller
public class CommentPageController {

	private final ReservationService reservationService;

	@Autowired
	public CommentPageController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/comment/{reservationInfoId}")
	public String getAddCommentPage(@PathVariable Long reservationInfoId, HttpServletRequest request,
			HttpSession session, ModelMap map) throws PageNotFoundException {

		String userEmail = (String) session.getAttribute("userEmail");
		if (!reservationService.findFinishReservation(reservationInfoId, userEmail)) {
			String url = request.getRemoteAddr() + request.getRequestURI();
			throw new PageNotFoundException(url);
		}

		ReservationInfoDto reservation = reservationService.getReservation(reservationInfoId);
		map.addAttribute("reservation", reservation);
		return "commentPage";
	}
}
