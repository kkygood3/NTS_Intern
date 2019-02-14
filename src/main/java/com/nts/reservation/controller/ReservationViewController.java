/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

	@PostMapping("/loginConfirm")
	public String Login(HttpSession session, @RequestBody MultiValueMap<String, String> formData) {
		session.setAttribute("userEmail", formData.getFirst("userEmail"));
		return "myreservation";
	}

	@GetMapping("/history")
	public String myReservationPage() {
		return "myreservation";
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
	public String reservePage(ModelMap model) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, (int)(Math.random() * 5 + 1));
		Date createdDate = calendar.getTime();
		String reservationDate = new java.text.SimpleDateFormat("yyyy.MM.dd").format(createdDate);

		model.addAttribute("reservationDate", reservationDate);
		return "reserve";
	}
}
