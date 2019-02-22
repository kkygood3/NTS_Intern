/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.service.impl.ReservationServiceImpl;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Controller
public class ReservationController {
	@Autowired
	ReservationServiceImpl reservationServiceImpl;

	@GetMapping("/reservation")
	public ModelAndView requestProductDetail(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		//공연 정보 날짜를 오늘부터 1~5일후의 날짜로 무작위 생성.
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, (int)((Math.random() * 5)) + 1);

		ModelAndView modelAndMap = new ModelAndView("reservation");
		modelAndMap.addObject("displayInfoId", displayInfoId);
		ReservationResponse reservationResponse = reservationServiceImpl.getReservationResponse(displayInfoId);
		modelAndMap.addObject("reservationDisplayInfo", reservationResponse.getReservationDisplayInfo());
		modelAndMap.addObject("reservationPrice", reservationResponse.getPrices());
		modelAndMap.addObject("reservationDate", dateFormat.format(calendar.getTime()));

		return modelAndMap;
	}

	@GetMapping("/bookingLogin")
	public String requestBookingLogin() {
		return "bookingLogin";
	}

	@PostMapping("/myReservation")
	public String loginReservation(@RequestParam(name = "email", required = true) String email,
		HttpSession session) {

		if (!ArgumentValidator.checkEmail(email)) {
			return "error";
		}

		session.setAttribute("email", email);
		return "myReservation";
	}

	@GetMapping("/myReservation")
	public String requestMyReservation(@RequestParam(name = "email", required = false) String email,
		HttpSession session) {
		String sessionEmail = (String)session.getAttribute("email");
		if (sessionEmail != null) {
			return "myReservation";
		} else {
			return "redirect:bookingLogin";
		}
	}
}