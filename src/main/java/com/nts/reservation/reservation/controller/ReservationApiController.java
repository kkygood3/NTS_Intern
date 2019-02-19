/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistoryResponse;
import com.nts.reservation.reservation.service.ReservationService;

@RestController
public class ReservationApiController {

	@Autowired
	private ReservationService reservationService;

	@GetMapping(value = {"/api/reservation-historys"})
	public ReservationHistoryResponse getReservationHistoryResponse(HttpSession httpSession) {

		String reservationEmail = (String)httpSession.getAttribute("email");

		return new ReservationHistoryResponse(reservationService.getReservationHistoryList(reservationEmail));
	}

	@PostMapping(value = {"/api/reservation"})
	public int addReservation(@RequestBody @Valid Reservation reservation) {
		return reservationService.addReservation(reservation);
	}

	@PutMapping(value = {"/api/reservation/{reservationId}/cancel"})
	public void modifyReservationToCancel(@PathVariable int reservationId) {
		reservationService.modifyReservationToCancel(reservationId);
	}
}
