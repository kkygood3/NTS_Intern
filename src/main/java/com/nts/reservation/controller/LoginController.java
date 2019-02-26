/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author  : 이승수
*/
@Controller
public class LoginController {
	@PostMapping("/loginConfirm")
	public String login(HttpSession session, @RequestParam(name = "userEmail", required = true) String userEmail) {
		session.setAttribute("userEmail", userEmail);
		return "redirect:/reservation/history";
	}
}
