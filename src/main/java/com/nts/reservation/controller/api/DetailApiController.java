/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.property.Properties;
import com.nts.reservation.service.DetailService;
import com.nts.reservation.service.ReviewService;

@RestController
@RequestMapping("/api/products/{displayInfoId}")
public class DetailApiController {
	@Autowired
	private DetailService detailDisplayService;
	@Autowired
	private ReviewService reviewResponseService;

	/**
	 * /api/products/{displayInfoId} 요청을 받아 detail, review 페이지에 출력
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return detailDisplay JSON text
	 */
	@GetMapping
	public Map<String, Object> getDisplayInfo(@PathVariable Integer displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = Properties.COMMENT_DEFAULT_START) Integer start,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = Properties.DETAIL_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		if (start < 0) {
			start = 0;
		}
		
		return Collections.singletonMap("detailResponse", detailDisplayService.getDetailResponse(displayInfoId, start, pagingLimit));
	}

	/**
	 * /api/products/{displayInfoId}/extra 요청을 받아 detail 페이지에 추가로 노출할 이미지를 출력 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @return productImage JSON text
	 */
	@GetMapping("/extra")
	public Map<String, Object> getExtraImage(@PathVariable Integer displayInfoId) {

		return Collections.singletonMap("extraImageResponse", detailDisplayService.getExtraImage(displayInfoId));
	}

	/**
	 * /api/products/{displayInfoId}/review 요청을 받아 해당 상품의 코멘트를 조회 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return comments JSON text
	 */
	@GetMapping("/review")
	public Map<String, Object> reviewComments(@PathVariable Integer displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = Properties.COMMENT_DEFAULT_START) Integer start,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = Properties.REVIEW_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {
		
		if (start < 0) {
			start = 0;
		}
		
		return Collections.singletonMap("reviewResponse", reviewResponseService.getReviewResponse(displayInfoId, start, pagingLimit));
	}
}
