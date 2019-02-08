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
	 * 상세페이지 URL 맵핑 
	 * @return 메인페이지
	 */
	@GetMapping(path = "/detail")
	public String detail(@RequestParam(name = "product_id", required = true) int productId,
		@RequestParam(name = "display_info_id", required = true) int displayInfoId,
		@RequestParam(name = "description", required = true) String description,
		@RequestParam(name = "save_file_name", required = true) String saveFileName,
		@RequestParam(name = "place_name", required = true) String placeName,
		@RequestParam(name = "content", required = true) String content,
		ModelMap model) {
		model.addAttribute("productId", productId);
		model.addAttribute("displayInfoId", displayInfoId);
		model.addAttribute("description", description);
		model.addAttribute("saveFileName", saveFileName);
		model.addAttribute("placeName", placeName);
		model.addAttribute("content", content);
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
