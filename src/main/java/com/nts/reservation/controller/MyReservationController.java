/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.constant.RegularExpression;
import com.nts.reservation.dto.response.MyReservationResponseDto;
import com.nts.reservation.service.ReservationService;
import com.nts.reservation.util.RegexValidator;

/**
 * 나의 예약 페이지 컨트롤러
 * @author jinwoo.bae
 */
@Controller
public class MyReservationController {
	@Autowired
	private ReservationService reservationService;
	
	@GetMapping("/myreservation")
	public String getMyReservationPage(@RequestParam String reservationEmail, HttpSession session, Model model) {
		if (!RegexValidator.isValid(RegularExpression.EMAIL_REGEXP, reservationEmail)) {
			throw new RuntimeException("이메일 형식이 틀렸습니다.");
		}
		MyReservationResponseDto myReservationResponse = reservationService.getMyReservations(reservationEmail);
		model.addAttribute("response", myReservationResponse);
		session.setAttribute("reservationEmail", reservationEmail);
		return "myreservation";
	}
}
