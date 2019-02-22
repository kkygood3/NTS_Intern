/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.commons.debugPrinter.DebugPrinter;
import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.dto.ReserveRequest;
import com.nts.reservation.reservation.service.impl.ReservationServiceImpl;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
	@Autowired
	ReservationServiceImpl reservationServiceImpl;

	// 예약 조회하기
	@GetMapping
	public ReservationResponse getReservationResponse(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		return reservationServiceImpl.getReservationResponse(displayInfoId);
	}

	// 예약 하기
	@PostMapping
	public boolean reserve(
		@RequestBody ReserveRequest reserveRequest) {
		ArgumentValidator.checkReserveRequest(reserveRequest);

		boolean isInsertComplete = reservationServiceImpl.insertReservationInfo(reserveRequest);
		if (!isInsertComplete) {
			DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 하기 실패");
		}
		DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 하기 성공");

		if (isInsertComplete) {
			return true;
		}
		return false;
	}

	// 나의 예약 조회하기
	@GetMapping("/my")
	public ReservationInfoResponse getReservationInfoResponse(
		@RequestParam(name = "email", required = true) String reservationEmail) {
		DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "email : " + reservationEmail);

		return reservationServiceImpl.getReservationInfoResponse(reservationEmail);
	}

	// 나의 예약 취소하기
	@PutMapping("/my/{reservationInfoId}")
	public boolean cancelReservation(@PathVariable int reservationInfoId) {
		boolean isUpdateComplete = reservationServiceImpl.cancelReservation(reservationInfoId);

		if (!isUpdateComplete) {
			DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 취소 실패");
		}
		DebugPrinter.print(Thread.currentThread().getStackTrace()[1], "예약 취소 성공");

		return true;
	}
}
