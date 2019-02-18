package com.nts.reservation.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * 예약페이지 url맵핑해주는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path="reservation")
public class ReservationController {
	@GetMapping
	public String myReservation(HttpSession session) {
		if (session.getAttribute("email") == null) {
			return "redirect:login";
		}
		return "myreservation";
	}
}
