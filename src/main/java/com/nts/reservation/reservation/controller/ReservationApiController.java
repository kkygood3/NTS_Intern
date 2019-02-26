/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.common.annotation.IsLogin;
import com.nts.reservation.common.model.Response;
import com.nts.reservation.reservation.model.Reservation;
import com.nts.reservation.reservation.model.ReservationHistoryResponse;
import com.nts.reservation.reservation.service.ReservationService;

@RestController
public class ReservationApiController {

	private final ReservationService reservationService;

	@Autowired
	public ReservationApiController(ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	/**
	 * 사용자의 예매내역 조회
	 */
	@IsLogin
	@GetMapping(value = {"/api/reservation-histories"})
	public ReservationHistoryResponse getReservationHistoryResponse(HttpSession httpSession) {

		String reservationEmail = (String)httpSession.getAttribute("email");

		return new ReservationHistoryResponse(reservationService.getReservationHistoryList(reservationEmail));
	}

	/**
	 * 상품 예매 정보 저장
	 */
	@PostMapping(value = {"/api/reservation"})
	public Response addReservation(@RequestBody @Validated Reservation reservation) {
		reservationService.addReservation(reservation);
		return new Response();
	}

	/**
	 * 상품 예매 취소
	 */
	@IsLogin
	@PutMapping(value = {"/api/reservation/{reservationId}/cancel"})
	public Response modifyReservationToCancel(HttpSession httpSession, @PathVariable int reservationId) {

		String reservationEmail = (String)httpSession.getAttribute("email");

		reservationService.modifyReservationToCancel(reservationEmail, reservationId);
		return new Response();
	}
}
