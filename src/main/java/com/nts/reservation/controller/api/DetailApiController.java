package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.service.DetailResponseService;

@RestController
public class DetailApiController {
	@Autowired
	private DetailResponseService detailDisplayService;

	/**
	 * /api/products/{displayInfoId} 요청을 받아 detail, review 페이지에 출력
	 * @return	JSON text
	 */
	@GetMapping("/api/products/{displayInfoId}")
	public Map<String, Object> getDisplayInfo(@PathVariable Integer displayInfoId) {
		
		DetailResponse detailDisplay = detailDisplayService.getDetailDisplay(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("detailDisplay", detailDisplay);

		return map;
	}
}
