package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.ReservationService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/")
public class ApplicationPutApiController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;

	@PutMapping(path = "/reservations/{reservationId}")
	public boolean postReservation(
		@PathVariable(name = "reservationId", required = true) Long reservationId) {
		reservationService.updateReservationCancelFlag(reservationId);
		return true;
	}

}
