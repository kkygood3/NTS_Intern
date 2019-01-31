package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationSystemController {
	@GetMapping(path = "/")
	public String main() {
		return "main";
	}
}
