package com.nts.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.nts.reservation.service.ProductService;

@Controller
public class ReservationController {
	@Autowired
	ProductService guestBookService;

	@GetMapping(path = "/")
	public String main() {
		return "main";
	}
}
