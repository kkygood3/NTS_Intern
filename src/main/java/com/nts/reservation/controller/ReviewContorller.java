package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewContorller {
	/**
	 * /review 요청을 받아 review view로 넘겨주는 역할.
	 * @param id - displayInfo 테이블의 id 
	 */
	@GetMapping("/review")
	public String requestDetailPage(@RequestParam(name = "id", required = true) Integer id) {
		return "review";
	}
}
