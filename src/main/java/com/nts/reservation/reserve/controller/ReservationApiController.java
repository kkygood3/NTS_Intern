/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationResponse;
import com.nts.reservation.reserve.service.ReservationService;

@RestController
@RequestMapping(path = "/api")
public class ReservationApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationApiController.class);

	private static final int NOT_UPDATED = 0;

	@Autowired
	ReservationService reservationService;

	/**
	 * 입력 받은 정보를 등록한 후, 등록된 예약 정보를 반환
	 * @param reservationParam 각 양식에 맞는 예약 정보
	 * @return
	 */
	@PostMapping(path = "/reservations")
	public ReservationResponse reserve(@RequestBody @Valid ReservationParam reservationParam, BindingResult result,
		HttpServletResponse response) {

		if (result.hasErrors()) {
			LOGGER.debug("{} / field : {} / code : {}", result.getFieldError().getDefaultMessage(),
				result.getFieldError().getField(), result.getFieldError().getCode());
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return ReservationResponse.builder().build();
		}
		String appliedStandardPhoneForm = addHyphenToPhone(reservationParam.getReservationTel());
		reservationParam.setReservationTel(appliedStandardPhoneForm);
		
		int reservationInfoId = reservationService.insertReservation(reservationParam); 
		return reservationService.getReservationResponse(reservationInfoId);
	}

	@PutMapping(path = "/reservations/{reservationId}")
	public ReservationResponse cancelReservation(@PathVariable(name = "reservationId") int reservationId,
		HttpSession session, HttpServletResponse response) {

		String reservationEmail = reservationService.getReservationResponse(reservationId).getReservationEmail();
		String email = (String)session.getAttribute("email");

		if (!reservationEmail.equals(email)) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			return ReservationResponse.builder().build();
		}

		int updateResult = reservationService.cancelReservation(reservationId);
		if (updateResult == NOT_UPDATED) {
			IllegalArgumentException e = new IllegalArgumentException("Does not exist reservationId!!");
			LOGGER.warn("Bad Request! Parameter / Error Message : {} / reservationId : {}", e.getMessage(),
				reservationId, e);
			throw e;
		}

		return reservationService.getReservationResponse(reservationId);
	}

	/**
	 * 입력받은 전화번호를 양식에 맞게 적용
	 */
	private String addHyphenToPhone(String phone) {
		if (phone.length() == 10) {
			return phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6);
		}
		return phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
	}
}
