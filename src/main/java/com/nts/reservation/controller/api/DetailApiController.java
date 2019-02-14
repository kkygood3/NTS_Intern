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
import com.nts.reservation.finalvariables.DefaultPagingLimit;
import com.nts.reservation.service.detail.DetailExtraImageService;
import com.nts.reservation.service.detail.DetailResponseService;

@RestController
public class DetailApiController {
	@Autowired
	private DetailResponseService detailDisplayService;
	@Autowired
	private DetailExtraImageService detailExtraImageService;

	/**
	 * /api/products/{displayInfoId} 요청을 받아 detail, review 페이지에 출력
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return detailDisplay JSON text
	 */
	@GetMapping("/api/products/{displayInfoId}")
	public Map<String, Object> getDisplayInfo(@PathVariable Integer displayInfoId,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DefaultPagingLimit.DETAIL_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		List<DetailResponse> detailDisplay = detailDisplayService.getDetailResponse(displayInfoId, pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("detailDisplay", detailDisplay);

		return map;
	}

	/**
	 * /api/products/{displayInfoId}/extra 요청을 받아 추가로 노출할 이미지를 출력 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @return productImage JSON text
	 */
	@GetMapping("/api/products/{displayInfoId}/extra")
	public Map<String, Object> getExtraImage(@PathVariable Integer displayInfoId) {
		DetailExtraImage productImage = detailExtraImageService.getExtraImage(displayInfoId);
		Map<String, Object> map = new HashMap<>();
		map.put("productImage", productImage);

		return map;
	}
}
