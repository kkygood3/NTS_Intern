package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * URL 맵핑하는 클래스
 * @author 시윤
 *
 */
@Controller
public class ReservationSystemController {
	/**
	 * 메인페이지 URL 맵핑 
	 * @return
	 */
	@GetMapping(path = "/")
	public String main() {
		return "main";
	}
}
