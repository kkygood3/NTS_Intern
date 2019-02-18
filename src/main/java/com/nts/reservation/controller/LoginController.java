package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping(path="/login")
	public String getLogin() {
		return "bookinglogin";
	}
	
	@PostMapping(path="/login")
	public String postLogin(@RequestParam(name="email", required=true) String email,
		HttpSession session) {
		session.setAttribute("email", email);
		return "redirect:/reservation";
	}
}
