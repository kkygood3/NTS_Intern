/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import static com.nts.reservation.constant.ParameterDefaultValue.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nts.reservation.annotation.PageDefault;
import com.nts.reservation.dto.param.PageDto;
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

	@GetMapping("/myreservation")
	public String getMyReservationPage(@PageDefault(limit = RESERVATIONS_LIMIT) PageDto page,
		HttpSession session, Model model) {

		String reservationEmail = (String)session.getAttribute("reservationEmail");

		MyReservationResponseDto myReservationResponse = reservationService.getMyReservationResponse(reservationEmail,
			page);
		model.addAttribute("response", myReservationResponse);

		return "myreservation";
	}
}
