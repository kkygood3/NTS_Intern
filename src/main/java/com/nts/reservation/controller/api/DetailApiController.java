package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.detail.DetailDisplay;
import com.nts.reservation.service.DetailDisplayService;

@RestController
public class DetailApiController {
	@Autowired
	private DetailDisplayService detailDisplayService;

	/**
	 * /categories 요청을 받아 메인 페이지에 카테고리 목록 출력
	 * @return	JSON text
	 */
	@GetMapping("/api/products/{displayInfoId}")
	public Map<String, Object> getDisplayInfo(
		@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		DetailDisplay detailDisplay = detailDisplayService.getDetailDisplay(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("detailDisplay", detailDisplay);

		return map;
	}
}
