/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.commons.validator.NegativeValueValidator;
import com.nts.reservation.displayinfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayinfo.service.impl.DisplayInfoServiceImpl;

@RestController
@RequestMapping("/api")
public class DisplayInfoApiController {

	@Autowired
	private DisplayInfoServiceImpl displayInfoServiceImpl;

	@RequestMapping(value = "/products/{displayInfoId}", method=RequestMethod.GET)
	public DisplayInfoResponse displayInfos(
		@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {

		if(NegativeValueValidator.isNegativeValue(displayInfoId, limit)) {
			System.out.printf("허용되지 않은 파라미터 시도입니다. displayInfoId : %d, limit : %d\n", displayInfoId, limit);
			displayInfoId = 1;
			limit = 3;
		}

		return displayInfoServiceImpl.getDisplayInfos(displayInfoId, limit);
	}
}
