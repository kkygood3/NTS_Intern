/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.dto.reservation.ReservationData;
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

	@Autowired
	private ReservationService reservationService;

	@GetMapping
	public ReservationInfos getReservationsByReservationEmail(@RequestParam(required = true) String reservationEmail)
		throws DisplayInfoNullException {

		if (CheckFormat.getInstance().validateEmailFormat(reservationEmail)) {
			return reservationService.getReservationInfoByReservationEmail(reservationEmail);
		} else {
			return new ReservationInfos();
		}
	}
	
	@PostMapping
	public boolean addReservations(@RequestBody(required=true) ReservationData reservationData) {
		
		reservationService.addReservation(reservationData);
		return true;
	}
	
	@PutMapping("/{reservationId}")
	public boolean modifyReservationToCancelByReservationId(@PathVariable(required=true) long reservationId) {
		
		reservationService.modifyReservationToCancelByReservationId(reservationId);
		return true;
	}
}
