/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.display.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayController {

	@GetMapping(path = "/display/detail/{displayInfoId}")
	public ModelAndView goDetail(@PathVariable(name = "displayInfoId") int displayInfoId) {
		ModelAndView modelAndView = new ModelAndView("detail");
		modelAndView.addObject("displayInfoId", displayInfoId);
		return modelAndView;
	}

}
