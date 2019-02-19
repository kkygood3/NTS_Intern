/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.service.ReservationService;

/**
 * 나의 예약 페이지 컨트롤러
 * @author jinwoo.bae
 */
@Controller
public class MyReservationController {
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("myreservation")
	public String getMyReservationPage(@RequestParam String reservationEmail, Model model) {
		MyReservationResponseDto myReservationResponse = reservationService.getMyReservations(reservationEmail);
		model.addAttribute("response", myReservationResponse);
		// TODO: 세션등록
		return "myreservation";
	}
}
