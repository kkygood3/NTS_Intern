package com.nts.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * URL 맵핑하는 클래스
 * @author 시윤
 *
 */
@Controller
public class ReservationSystemController {
	/**
	 * 메인페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/")
	public String main() {
		return "main";
	}

	/**
	 * 에러페이지 URL 맵핑 
	 * @return 에러페이지
	 */
	@GetMapping(path = "/error")
	public String error() {
		return "error";
	}
}
