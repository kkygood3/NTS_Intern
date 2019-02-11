package com.nts.reservation.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.MainPromotion;
import com.nts.reservation.service.MainPromotionService;

@RestController
public class PromotionApiController {
	@Autowired
	private MainPromotionService mainPromotionService;

	/**
	 * /api/promotions 요청을 받아 메인 페이지에 프로모션 정보를 출력
	 * @return	JSON text
	 */
	@GetMapping("/api/promotions")
	public Map<String, Object> promotions() {

		List<MainPromotion> promotionList = new ArrayList<>();
		int totalCount = mainPromotionService.getCount();

		if (totalCount > 0) {
			promotionList = mainPromotionService.getPromotions();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("promotionList", promotionList);
		map.put("totalCount", totalCount);

		return map;
	}
}
