package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.service.validation.Validator;

/**
 * 로그인 관련 컨트롤러 클래스
 * @author si yoon
 *
 */
@Controller
public class LoginController {
	/**
	 * 로그인 화면으로 연결
	 * @return
	 */
	@GetMapping(path = "/login")
	public String getLogin() {
		return "bookinglogin";
	}

	/**
	 * 로그인정보를 저장하고 예약확인 페이지로 이동
	 * @param email 사용자 이메일
	 * @param session http session
	 * @return
	 */
	@PostMapping(path = "/login")
	public String postLogin(@RequestParam(name = "email", required = true) String email,
		HttpSession session) {
		if (!Validator.validateEmail(email)) {
			return "redirect:/error";
		}
		session.setAttribute("email", email);
		return "redirect:/reservation";
	}
}
