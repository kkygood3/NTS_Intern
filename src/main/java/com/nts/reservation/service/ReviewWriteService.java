/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.reviewwrite.ReviewWriteResponse;

public interface ReviewWriteService {
	/**
	 * reviewwrite 페이지 로드시 필요한 정보 획득
	 * @param reservationInfoId
	 */
	ReviewWriteResponse getReviewWriteResponse(int reservationInfoId);
}
