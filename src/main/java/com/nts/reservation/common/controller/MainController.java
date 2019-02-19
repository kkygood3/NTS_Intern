/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.display.dto.DisplayResponse;
import com.nts.reservation.display.service.DisplayService;

@Controller
public class MainController {

	@Autowired
	DisplayService displayService;

	@GetMapping(path = "/main")
	public String goMain() {
		return "mainpage";
	}

	@GetMapping(path = "/review/{displayInfoId}")
	public ModelAndView goReview(@PathVariable(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("review");
		modelAndView.addObject("displayInfoId", displayInfoId);
		return modelAndView;
	}

	@GetMapping(path = "/display/detail/{displayInfoId}")
	public ModelAndView goDetail(@PathVariable(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("detail");
		modelAndView.addObject("displayInfoId", displayInfoId);
		return modelAndView;
	}

	@GetMapping(path = "/reserve/{displayInfoId}")
	public ModelAndView goReserve(@PathVariable(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("reserve");
		DisplayResponse displayResponse = displayService.getDisplayInfo(displayInfoId, 0);
		modelAndView.addObject("displayResponse", displayResponse);
		return modelAndView;
	}

	@GetMapping(path = "/myReserve")
	public String goMyReserve() {
		return "myreservation";
	}

	@GetMapping(path = "/error")
	public String goError() {
		return "common/error";
	}
}
