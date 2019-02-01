package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * 메인메이지 url 맵핑
 * @author 시윤
 */
@Controller
public class ReservationSystemController {
	@GetMapping(path = "/")
	public String main() {
		return "main";
	}
}
