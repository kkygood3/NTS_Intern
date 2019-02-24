/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.service.ReservationService;

/**
 * @author 육성렬
 */
@Controller
public class AddCommentPageController {

	private final ReservationService reservationService;

	@Autowired
	public AddCommentPageController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping("/addComment/{reservationInfoId}")
	public String getAddCommentPage(@PathVariable Long reservationInfoId, ModelMap map) {
		ReservationInfoDto reservation = reservationService.getReservation(reservationInfoId);
		map.addAttribute("reservation", reservation);
		return "addCommentPage";
	}
}
