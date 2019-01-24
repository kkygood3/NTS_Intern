/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.nts.reservation.service.ProductService;

@Controller
public class MainController {
	@Autowired
	ProductService productService;

	@GetMapping(path = "/")
	public String test(ModelMap model) {
		int count = productService.getCount();
		model.addAttribute("countTest", count);
		return "mainpage";
	}
}
