package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewContorller {
	
	@GetMapping("/review")
	public String requestDetailPage(@RequestParam(name = "id", required = true) Integer id) {
		return "review";
	}
}
