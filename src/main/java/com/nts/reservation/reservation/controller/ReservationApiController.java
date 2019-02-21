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

import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
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
	public ReservationInfoResponse getReservation(
		@RequestParam(name = "email", required = true) String reservationEmail) {

		return reservationServiceImpl.getReservationInfoResponse(reservationEmail);
	}

	// 예약 하기
	// RequestParam 대신 RequestBody 사용하기
	// priceInfo

	//	@PostMapping
	//	public void reserve(
	//		@RequestParam(name = "reservationName", required = true) String reservationName,
	//		@RequestParam(name = "reservationTel", required = true) String reservationTel,
	//		@RequestParam(name = "reservationEmail", required = true) String reservationEmail,
	//		@RequestParam(name = "displayInfoId", required = true) int displayInfoId,
	//		//
	//		@RequestParam(name = "priceInfo", required = true) String priceInfo,
	//		@RequestParam(name = "reservationDate", required = true) String reservationDate) {
	//
	//		// TO-DO 스크립트로 alert 메시지
	//		boolean isInsertComplete = reservationServiceImpl.insertReservation(reservationName, reservationTel,
	//			reservationEmail, displayInfoId, priceInfo, reservationDate);
	//		if (isInsertComplete) {
	//			System.out.println("예약 성공");
	//		} else {
	//			System.out.println("예약 실패");
	//		}
	//	}

	@PostMapping
	public void reserve(@RequestBody ReserveRequest reserveRequest) {
		ArgumentValidator.checkReserveRequest(reserveRequest);
		System.out.println(reserveRequest.getReservationName());
		// TO-DO 스크립트로 alert 메시지
		boolean isInsertComplete = reservationServiceImpl.insertReservationInfo(reserveRequest);
		if (isInsertComplete) {
			System.out.println(Thread.currentThread().getStackTrace()[1].getFileName());
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			System.out.println("예약 성공");
		} else {
			System.out.println(Thread.currentThread().getStackTrace()[1].getFileName());
			System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
			System.out.println("예약 실패");
		}
	}

	// 예약 취소하기
	@PutMapping("/{reservationInfoId}")
	public void cancelReservation(@PathVariable int reservationInfoId) {
		boolean isUpdateComplete = reservationServiceImpl.cancelReservation(reservationInfoId);

		// TO-DO 스크립트로 alert 메시지
		if (isUpdateComplete) {
			System.out.println("취소 성공");
		} else {
			System.out.println("취소 실패");
		}
	}
}
