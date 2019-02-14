/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.dto.review.ReviewResponse;
import com.nts.reservation.property.DefaultPagingLimitProperties;
import com.nts.reservation.service.review.ReviewResponseService;

@RestController
public class ReviewApiController {

	@Autowired
	private ReviewResponseService reviewResponseService;

	/**
	 * /api/products/{displayInfoId}/review 요청을 받아 해당 상품의 코멘트를 조회 
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return comments JSON text
	 */
	@GetMapping("/api/products/{displayInfoId}/review")
	public Map<String, Object> reviewComments(@PathVariable Integer displayInfoId,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DefaultPagingLimitProperties.REVIEW_DEFAULT_PAGING_LIMIT) Integer pagingLimit) {

		List<ReviewResponse> comments = reviewResponseService.getReviewResponse(displayInfoId, pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("comments", comments);
		return map;
	}
}
