/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.detail.DetailExtraImage;
import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.dto.review.ReviewResponse;
import com.nts.reservation.property.DefaultPagingLimitProperties;
import com.nts.reservation.property.ReviewProperties;
import com.nts.reservation.service.detail.DetailExtraImageService;
import com.nts.reservation.service.detail.DetailResponseService;
import com.nts.reservation.service.review.ReviewResponseService;

@RestController
@RequestMapping("/api/products/{displayInfoId}")
public class ProductDetailApiController {
	@Autowired
	private DetailResponseService detailDisplayService;
	@Autowired
	private DetailExtraImageService detailExtraImageService;
	@Autowired
	private ReviewResponseService reviewResponseService;

	/**
	 * /api/products/{displayInfoId} 요청을 받아 detail, review 페이지에 출력
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return detailDisplay JSON text
	 */
	@GetMapping
	public Map<String, Object> getDisplayInfo(@PathVariable Integer displayInfoId,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DefaultPagingLimitProperties.DETAIL_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		DetailResponse detailDisplay = detailDisplayService.getDetailResponse(displayInfoId, pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("detailDisplay", detailDisplay);

		return map;
	}

	/**
	 * /api/products/{displayInfoId}/extra 요청을 받아 detail 페이지에 추가로 노출할 이미지를 출력 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @return productImage JSON text
	 */
	@GetMapping("/extra")
	public Map<String, Object> getExtraImage(@PathVariable Integer displayInfoId) {
		DetailExtraImage productImage = detailExtraImageService.getExtraImage(displayInfoId);

		Map<String, Object> map = new HashMap<>();
		map.put("productImage", productImage);

		return map;
	}

	/**
	 * /api/products/{displayInfoId}/review 요청을 받아 해당 상품의 코멘트를 조회 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return comments JSON text
	 */
	@GetMapping("/review")
	public Map<String, Object> reviewComments(@PathVariable Integer displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = ReviewProperties.PRODUCT_DEFAULT_START) Integer start,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DefaultPagingLimitProperties.REVIEW_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		List<ReviewResponse> comments = reviewResponseService.getReviewResponse(displayInfoId, start, pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("comments", comments);
		return map;
	}
	
	/**
	 * /api/products 요청시 결과값이 없을때 발생하는 예외 처리
	 * @return emptyMap
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public Map<String, Object> handleEmptyResult() {
		return Collections.emptyMap();
	}
}
