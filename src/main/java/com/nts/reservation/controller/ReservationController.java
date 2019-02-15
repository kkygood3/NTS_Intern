package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 예약페이지 url맵핑해주는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path="reservation")
public class ReservationController {
	@GetMapping
	public String myReservation() {
		return "myreservation";
	}
}
