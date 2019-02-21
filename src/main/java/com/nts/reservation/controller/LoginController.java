/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 로그인 처리 컨트롤러
 * @author jinwoo.bae
 */
@Controller
public class LoginController {

	/**
	 * 세션을 생성해주고 나의예약페이지로 이동시킵니다.
	 * 별도의 비밀번호 인증이 없습니다.
	 */
	@GetMapping("/doLogin")
	public String makeSession(@RequestParam String reservationEmail,
		HttpSession session) {
		session.setAttribute("reservationEmail", reservationEmail);
		return "redirect:/myreservation?reservationEmail=" + reservationEmail;
	}
}
