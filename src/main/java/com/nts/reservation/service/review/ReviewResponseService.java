package com.nts.reservation.service.review;

import java.util.List;

import com.nts.reservation.dto.review.ReviewResponse;

public interface ReviewResponseService {
	/**
	 * api/products/{diplayInfoId}/review response에 사용
	 * @return displayInfoId에 속하는 commentList
	 */
	List<ReviewResponse> getReviewResponse(int displayInfoId, int pagingLimit);
}
