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
import com.nts.reservation.service.review.ReviewResponseService;

@RestController
public class ReviewApiController {
	private final String DEFAULT_PAGING_LIMIT = "20";
	
	@Autowired
	private ReviewResponseService reviewResponseService;
	
	@GetMapping("/api/products/{displayInfoId}/review")
	public Map<String, Object> reviewComments(@PathVariable Integer displayInfoId,
		@RequestParam(name = "pagingLimit", required = false, defaultValue = DEFAULT_PAGING_LIMIT) Integer pagingLimit) {
		
		List<ReviewResponse> comments = reviewResponseService.getReviewResponse(displayInfoId, pagingLimit);
		Map<String, Object> map = new HashMap<>();
		map.put("comments", comments);
		return map;
	}
}
