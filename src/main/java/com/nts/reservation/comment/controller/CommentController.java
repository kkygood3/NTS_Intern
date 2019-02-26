/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.commons.validator.ArgumentValidator;
import com.nts.reservation.reservation.dto.ReservationResponse;
import com.nts.reservation.reservation.service.ReservationService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Controller
public class CommentController {
	@Autowired
	ReservationService reservationServiceImpl;

	@GetMapping("/detailComment")
	public String requestProductDetailComment(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		return "detailComment";
	}

	@GetMapping("/reviewWrite/{reservationInfoId}")
	public ModelAndView postProductDetailComment(
		@PathVariable int reservationInfoId, ModelAndView mav, HttpSession session) {
		ArgumentValidator.checkReservationInfoId(reservationInfoId);

		String email = (String)session.getAttribute("email");
		if (checkEmail(email)) {
			ReservationResponse reservationResponse = reservationServiceImpl.getReservationResponse(reservationInfoId);
			mav.addObject("reservationResponse", reservationResponse);
			mav.setViewName("reviewWrite");
		}
		return mav;
	}

	private static final String REGULAR_EMAIL = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
	private static final int MAX_EMAIL_LENGTH = 50;

	private static boolean checkEmail(String reservationEmail) {
		if (reservationEmail == null) {
			return false;
		}
		if (reservationEmail.length() > MAX_EMAIL_LENGTH) {
			return false;
		}
		if (!Pattern.matches(REGULAR_EMAIL, reservationEmail.trim())) {
			return false;
		}
		return true;
	}
}
