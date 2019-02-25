package com.nts.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.reservation.ReservationParam;
import com.nts.reservation.service.ReservationService;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

@RestController
@RequestMapping(path = "/api/")
public class ReservationApiController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;

	@PostMapping(path = "/reservations")
	public void postReservation(@RequestBody ReservationParam reservationParam) {
		reservationService.addReservations(reservationParam);
	}

	@PutMapping(path = "/reservations/{reservationId}")
	public void cancelReservation(
		@PathVariable(name = "reservationId", required = true) Long reservationId) {
		reservationService.cancelReservation(reservationId);
	}

	@GetMapping("/reservations")
	public Map<String, Object> getReservations() {
		Map<String, Object> result = new HashMap<>();
		result.put("reservations", reservationService.getReservations(session.getAttribute("email").toString()));
		return result;
	}

}
