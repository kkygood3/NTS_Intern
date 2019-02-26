/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentWriteController {

	@RequestMapping(value = "/writePage", method = RequestMethod.GET)
	public ModelAndView moveReservePage(@RequestParam(name = "productId", required = true) int productId,
		HttpSession session) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("productId", productId);
		mav.addObject("email", session.getAttribute("Email"));
		mav.setViewName("write");
		return mav;
	}

}
