/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 전연빈
 */
@Controller
@RequestMapping("/reserve")
public class ReserveController {

	@GetMapping("/{displayInfoId}")
	public ModelAndView showReservePage(@PathVariable int displayInfoId, ModelAndView modelAndView) {

		modelAndView.addObject("displayInfoId", displayInfoId);
		modelAndView.setViewName("reserve");
		return modelAndView;
	}
}
