package com.nts.reservation.controller;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.service.ReservationService;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

@RestController
@RequestMapping(path = "/api/")
public class ApplicationLoginApiController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;

	@PostMapping(path = "/login")
	public Boolean loginProcess(@RequestBody String resrv_email) {
		session.setAttribute("email", resrv_email);
		return true;
	}
}
