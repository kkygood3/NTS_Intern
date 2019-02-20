package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.service.validation.Validator;

/**
 * 로그인 관련 컨트롤러 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path = "/login")
public class LoginController {
	/**
	 * 로그인 화면으로 연결
	 * @return
	 */
	public String getLogin() {
		return "login";
	}

	/**
	 * 로그인정보를 저장하고 예약확인 페이지로 이동
	 * @param email 사용자 이메일
	 * @param session http session
	 * @return
	 */
	public String postLogin(@RequestParam(name = "email", required = true) String email,
		HttpSession session) {
		if (!Validator.validEmail(email)) {
			return "redirect:/error";
		}
		session.setAttribute("email", email);
		return "redirect:/reservation";
	}
}
