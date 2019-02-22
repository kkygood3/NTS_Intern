/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nts.reservation.reservation.service.ReservationService;

@Controller
public class ReservationListController {

	@Autowired
	ReservationService reservationServiceImpl;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String moveListPage() {
		return "list";
	}

}
