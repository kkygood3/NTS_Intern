/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nts.reservation.commons.validator.ArgumentValidator;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Controller
public class ProductController {
	@GetMapping("/detailProduct")
	public ModelAndView requestProductDetail(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		ModelAndView modelAndMap = new ModelAndView("detailProduct");
		modelAndMap.addObject("displayInfoId", displayInfoId);

		return modelAndMap;
	}
}