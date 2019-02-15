/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.commons.validator.NegativeValueValidator;

@Controller
public class DisplayInfoController {

	@GetMapping("/detail")
	public String requestDetailPage(@RequestParam(name = "id", required = true) Integer id) {

		if(NegativeValueValidator.isNegativeValue(id)) {
			throw new IllegalArgumentException("id : " + id );
		}

		return "detail";
	}

}
