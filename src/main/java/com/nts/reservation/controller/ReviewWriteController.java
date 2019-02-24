/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewWriteController {
	/**
	 * /myreservation에서 productId를 받아서 상품의 review를 쓰는 페이지.
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/reviewWrite")
	public String requestReview(@RequestParam(name = "id", required = true) Integer id, HttpSession session) {
		/**
		 * productId, reserationInfoId, productDescription을 넘겨야 한다.
		 */
		
		
		return "reviewWrite";
	}
}
