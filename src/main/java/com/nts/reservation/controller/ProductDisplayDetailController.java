/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductDisplayDetailController {

	@GetMapping(value = {"/product-display"})
	public ModelAndView productDisplayDetailPage(ModelAndView mv, int displayInfoId) {
		mv.addObject("displayInfoId", displayInfoId);
		mv.setViewName("detail");
		return mv;
	}

	@GetMapping(value = {"/product-display/comments"})
	public ModelAndView productDisplayCommentListPage(ModelAndView mv, int displayInfoId) {
		mv.addObject("displayInfoId", displayInfoId);
		mv.setViewName("comments");
		return mv;
	}
}
