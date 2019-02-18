/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.param.ReservationParamDto;
import com.nts.reservation.service.ReservationService;

/**
 * 예약 API
 * @author jinwoo.bae
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {
	@Autowired
	private ReservationService reservationService;

	// TODO: 파라미터 검증 추후 구현
	@PostMapping
	public Map<String, Object> postReservation(@RequestBody ReservationParamDto reservationParam) {
		reservationService.makeReservation(reservationParam);
		return Collections.singletonMap("isSuccess", true);
	}
}
