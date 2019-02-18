/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainContorller {
	/**
	 * /detail 요청을 받아 detail view로 넘겨주는 역할.
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/")
	public String requestDetail(HttpSession session, ModelMap map) {
		String email = (String)session.getAttribute("email");
		if (email != null) {
			map.addAttribute("email", email);
		}
		return "main";
	}
}
