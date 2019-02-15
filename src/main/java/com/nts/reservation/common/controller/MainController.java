/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@GetMapping(path = "/main")
	public String goMain() {
		return "mainpage";
	}

	@GetMapping(path = "/review")
	public ModelAndView goReview(@RequestParam(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("review");
		modelAndView.addObject("displayInfoId", displayInfoId);
		return modelAndView;
	}

	@GetMapping(path = "/detail")
	public ModelAndView goDetail(@RequestParam(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("detail");
		modelAndView.addObject("displayInfoId", displayInfoId);
		return modelAndView;
	}
	
	@GetMapping(path = "/error")
	public String goError() {
		return "common/error";
	}
}
