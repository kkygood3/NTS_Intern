package com.nts.reservation.service.review;

import java.util.List;

import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.dto.review.ReviewResponse;

public interface ReviewResponseService {
	
	List<ReviewResponse> getReviewResponse(int displayInfoId, int limit);
}
