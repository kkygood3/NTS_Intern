/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailContorller {
	/**
	 * /detail 요청을 받아 detail view로 넘겨주는 역할.
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/detail")
	public String requestDetail(@RequestParam(name = "id", required = true) Integer id, HttpSession session,
		ModelMap map) {
		String email = (String)session.getAttribute("email");
		if (email != null) {
			map.addAttribute("email", email);
		}
		return "detail";
	}
}
