package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.detail.DetailExtraImage;
import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.service.detail.DetailExtraImageService;
import com.nts.reservation.service.impl.detail.DetailResponseServiceImpl;

@RestController
public class DetailApiController {
	private final String DEFAULT_PAGING_LIMIT = "3";
	@Autowired
	private DetailResponseServiceImpl detailDisplayService;
	@Autowired
	private DetailExtraImageService detailExtraImageService;

	/**
	 * /api/products/{displayInfoId} 요청을 받아 detail, review 페이지에 출력
	 * @return	JSON text
	 */
	@GetMapping("/api/products/{displayInfoId}")
	public Map<String, Object> getDisplayInfo(@PathVariable Integer displayInfoId,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		List<DetailResponse> detailDisplay = detailDisplayService.getDetailDisplay(displayInfoId,pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("detailDisplay", detailDisplay);

		return map;
	}

	@GetMapping("/api/products/{displayInfoId}/extra")
	public Map<String, Object> getExtraImage(@PathVariable Integer displayInfoId) {
		DetailExtraImage productImage = detailExtraImageService.getExtraImage(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("productImage", productImage);

		return map;
	}
}
