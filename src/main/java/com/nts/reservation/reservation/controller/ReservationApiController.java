/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationApiController {

	@Autowired
	ReservationService reservationServiceImpl;

	@RequestMapping(value = "/reservations", method=RequestMethod.GET)
	public ReservationInfoResponse displayInfos(
		@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {

		return reservationServiceImpl.getReservationInfoResponse(reservationEmail);
	}

	@RequestMapping(value = "/reservations", method=RequestMethod.POST)
	public Map<String, Object> reserve(
		@RequestBody ReservationParam reserveRequest) {
		Map<String, Object> map = new HashMap<>();

		if (reserveRequest.isValid(reserveRequest) && reservationServiceImpl.postReserve(reserveRequest)) {
			map.put("result", "OK");
		} else {
			map.put("result", "FAIL");
		}

		return map;
	}



}
