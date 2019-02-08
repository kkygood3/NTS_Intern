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
	 * @return 메인페이지
	 */
	@GetMapping(path = "/")
	public String main() {
		return "main";
	}
	/**
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/detail")
	public String detail() {
		return "detail";
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
