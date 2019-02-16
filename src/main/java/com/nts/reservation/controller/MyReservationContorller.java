/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyReservationContorller {
	/**
	 * /myreservation 요청을 받아 myreservation view로 넘겨주는 역할.
	 */
	@GetMapping("/myreservation")
	public String requestMyReservation(@RequestParam(name = "email", required = false) String email,
		HttpSession session) {
		/*
		 * 로그인 상태라면 myreservation view를 출력.
		 * 비로그인 상태라면 bookinglogin view를 출력.
		 */
		String sessionEmail = (String)session.getAttribute("email");
		if (sessionEmail != null) {
			return "myreservation";
		} else {
			return "redirect:bookinglogin";
		}
	}

	/**
	 * bookinglogin에서 비회원 조회 요청을 받고 myreservation 페이지로 넘김. 
	 */
	@PostMapping("/myreservation")
	public String loginReservation(@RequestParam(name = "resrv_email", required = true) String email, HttpSession session, ModelMap map) {
		session.setAttribute("email", email);
		map.addAttribute("email", email);
		return "myreservation";
	}
}
