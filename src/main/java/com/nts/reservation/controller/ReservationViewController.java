/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
* @author  : 이승수
*/
@Controller
public class ReservationViewController {
	@GetMapping("/")
	public String mainPage() {
		return "mainpage";
	}

	@GetMapping("/login")
	public String bookingLoginPage() {
		return "bookinglogin";
	}

	@GetMapping("/detail/{displayInfoId}")
	public String detailPage() {
		return "detail";
	}

	@GetMapping("/detail/{displayInfoId}/reviews")
	public String reviewPage() {
		return "review";
	}

	@GetMapping("/detail/{displayInfoId}/reserve")
	public String reservePage() {
		return "reserve";
	}
}
