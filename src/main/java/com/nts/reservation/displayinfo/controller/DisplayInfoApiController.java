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
import com.nts.reservation.displayinfo.service.DisplayInfoService;

@RestController
@RequestMapping("/api")
public class DisplayInfoApiController {

	@Autowired
	private DisplayInfoService displayInfoServiceImpl;

	@RequestMapping(value = "/products/{displayInfoId}", method=RequestMethod.GET)
	public DisplayInfoResponse displayInfos(
		@PathVariable("displayInfoId") int displayInfoId,
		@RequestParam(name = "count", required = false, defaultValue = "3") int count) {

		if(NegativeValueValidator.isNegativeValue(displayInfoId, count)) {
			throw new IllegalArgumentException("displayInfoId : " + displayInfoId );
		}

		return displayInfoServiceImpl.getDisplayInfos(displayInfoId, count);
	}
}
