package com.nts.reservation.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.user.UserParam;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

@RestController
@RequestMapping(path = "/api/")
public class LoginApiController {

	@Autowired
	private HttpSession session;

	@PostMapping(path = "/login")
	public void loginProcess(UserParam userParam) {
		session.setAttribute("email", userParam.getEmail());
	}

	@PostMapping(path = "/logout")
	public void logoutProcess() {
		session.invalidate();
	}
}
