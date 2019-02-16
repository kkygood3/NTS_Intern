/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.review.ReviewResponse;

public interface ReviewResponseService {
	/**
	 * api/products/{diplayInfoId}/review response에 사용
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return ReviewResponse
	 */
	ReviewResponse getReviewResponse(int displayInfoId, int start, int pagingLimit);
}
