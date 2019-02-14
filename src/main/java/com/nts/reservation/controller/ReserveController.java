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
@RequestMapping(path="reservation")
public class ReserveController {
	@GetMapping(path = "/{productId}")
	public String reservation(@PathVariable(name="productId", required= true) long productId,
		ModelMap model) {
		
		return "reservation";
	}
	
	@GetMapping
	public String myReservation() {
		return "myreservation";
	}
}
