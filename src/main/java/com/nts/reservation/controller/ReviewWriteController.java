/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nts.reservation.service.ReviewWriteService;

@Controller
public class ReviewWriteController {
	@Autowired
	ReviewWriteService reviewWriteService;

	/**
	 * /myreservation에서 productId를 받아서 상품의 review를 쓰는 페이지.
	 * 비회원 로그인이 아니라면 bookinglogin Page로 리다이렉트
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/reviewWrite")
	public String requestReview(@RequestParam(name = "id", required = true) Integer id, ModelMap map,
		HttpSession session) {
		if (session.getAttribute("email") != null) {
			map.addAttribute("reviewWriteResponse", reviewWriteService.getReviewWriteResponse(id));
			return "reviewWrite";
		} else {
			return "redirect:bookinglogin";
		}
	}
}
