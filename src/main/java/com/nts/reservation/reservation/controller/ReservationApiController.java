/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
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

}
