/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.reserve.dto.ReservationResponse;
import com.nts.reservation.reserve.service.ReservationService;

@Controller
public class CommentController {

	@Autowired
	ReservationService reservationService;

//	private static final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);

	@GetMapping(path = "/review/{displayInfoId}")
	public ModelAndView goReview(@PathVariable(name = "displayInfoId") int displayInfoId, ModelAndView mav) {
		mav.setViewName("review");
		mav.addObject("displayInfoId", displayInfoId);
		return mav;
	}

	@GetMapping(path = "/reservations/{reservationInfoId}/comments")
	public ModelAndView goWriteForm(@PathVariable(name = "reservationInfoId") int reservationInfoId, ModelAndView mav) {
		// session, id 유무
		ReservationResponse reservationResponse = reservationService.getReservationResponse(reservationInfoId);
		mav.addObject(reservationResponse);
		mav.setViewName("reviewWrite");
		return mav;
	}
}
