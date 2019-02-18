package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 예약페이지 url맵핑해주는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path = "/reservation")
public class ReservationController {
	@GetMapping
	public String myReservation(HttpSession session, ModelMap model) {
		String email = (String) session.getAttribute("email");
		if (email == null) {
			return "redirect:login";
		}
		return "myreservation";
	}
}
