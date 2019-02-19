/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import java.security.InvalidParameterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.reservation.ReservationParameter;
import com.nts.dto.reservation.ReservationInfos;
import com.nts.exception.DisplayInfoNullException;
import com.nts.service.reservation.ReservationService;
import com.nts.util.CheckFormat;

/**
 * @author 전연빈
 */
@RequestMapping("/api/reservations")
@RestController
public class ReservationController {

	private ReservationService reservationService;
	
	public ReservationController(@Autowired ReservationService reservationService) {
		this.reservationService = reservationService;
	}

	@GetMapping
	public ReservationInfos getReservationsByReservationEmail(@RequestParam(required = true) String reservationEmail)
		throws DisplayInfoNullException {

		if (CheckFormat.validateEmailFormat(reservationEmail)) {
			return reservationService.getReservationInfoByReservationEmail(reservationEmail);
		} else {
			System.err.println("reservationEmail 형식이 맞지않습니다" + reservationEmail);
			throw new InvalidParameterException("reservationEmail = " + reservationEmail);
		}
	}
	
	@PostMapping
	public boolean addReservations(@RequestBody(required=true) ReservationParameter reservationParameter) {
		
		reservationService.addReservation(reservationParameter);
		return true;
	}
	
	@PutMapping("/{reservationId}")
	public boolean cancelReservation(@PathVariable(required=true) long reservationId) {
		
		reservationService.cancelReservation(reservationId);
		return true;
	}
}
