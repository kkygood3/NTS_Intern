/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reserve.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	ReservationService reservationService;

	// TODO A, Y, B 등 type enum 처리
	@PostMapping(path = "/reservations")
	public ReservationResponse reserve(@RequestBody ReservationParam reservationParam) {
		// TODO ReservationParam -> displayInfoId, reservationEmail, reservationName, reservationTel, ReservationPrice
			// ReservationPrice -> count, productPriceId
		
		// validate param (use StringUtils) 효율적인 방법 찾아보기
		
		// ReservationService (@Transactional(readOnly=false) 사용) -> readOnly 기능 다시한번 보기
		
		return reservationService.insertAndReturnRerservationParam(reservationParam);
	}
	
	@PutMapping(path = "/reservations/{reservationId}")
	public ReservationResponse cancelReservation(@PathVariable(name="reservationId") int reservationId) {
		int result = reservationService.cancelReservation(reservationId);
		
		if(result != 1) {
			// TODO exception?
			LOGGER.warn("Update Fail!! / reservationId : {}", reservationId);
		}
		
		return reservationService.getReservationResponse(reservationId);
	}
}
