/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.common.annotation.MustLogin;

@Controller
public class ReservationController {

	@GetMapping(value = {"/reserve"})
	public ModelAndView reserve(ModelAndView mv, int displayInfoId) {
		mv.addObject("displayInfoId", displayInfoId);
		return mv;
	}

	@MustLogin
	@GetMapping(value = {"/my-reservation"})
	public String myreservation() {
		return "myreservation";
	}
}
