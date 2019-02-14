package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 예약페이지 url맵핑해주는 클래스
 * @author si yoon
 *
 */
@Controller
@RequestMapping(path="detail/{displayInfoId}/reservation")
public class ReserveController {
	@GetMapping
	public String reservation(@PathVariable(name="displayInfoId", required= true) long displayInfoId,
		ModelMap model) {
		return "reservation";
	}
	
	@GetMapping(path = "/{reservationEmail}")
	public String myReservation() {
		return "myreservation";
	}
}
