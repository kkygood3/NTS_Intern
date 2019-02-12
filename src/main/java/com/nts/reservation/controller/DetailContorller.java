package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DetailContorller {

	@GetMapping("/detail")
	public String requestDetailPage(@RequestParam(name = "id", required = true) Integer id) {
		return "detail";
	}
}
