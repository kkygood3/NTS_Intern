/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import static com.nts.reservation.constant.ParameterDefaultValue.*;
import static com.nts.reservation.constant.RegularExpression.*;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.util.RegexValidator;

/**
 * 로그인 처리 컨트롤러
 * @author jinwoo.bae
 */
@Controller
public class LoginController {

	/**
	 * 예매자의 이메일 정규식 검증후 실패시 페이지에서 출력할 에러메시지를 넣어주고 로그인 페이지로이동시킵니다.
	 * 검증이 성공한다면 세션을 생성해주고 리다이렉트 페이지로 이동시킵니다.
	 */
	@GetMapping("/doLogin")
	public String makeSession(@RequestParam String reservationEmail,
		@RequestParam(required = false, defaultValue = REDIRECT_URL_AFTER_LOGIN) String redirectUrl,
		Model model, HttpSession session) {
		
		if (!RegexValidator.isValid(EMAIL_REGEXP, reservationEmail)) {
			model.addAttribute("isError", true);
			model.addAttribute("errorMsg", "올바르지않은 이메일 형식입니다.");
			return "/bookinglogin";
		}

		session.setAttribute("reservationEmail", reservationEmail);
		return "redirect:/myreservation";
	}
}
