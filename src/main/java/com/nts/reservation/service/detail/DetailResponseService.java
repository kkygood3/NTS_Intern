/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.detail;

import com.nts.reservation.dto.detail.DetailResponse;

public interface DetailResponseService {
	/**
	 * api/products/{diplayInfoId} response에 사용
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param start	페이지에 출력할 데이터의 시작 index
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return displayInfoId에 속하는 displayInfo, commentList
	 */
	DetailResponse getDetailResponse(int displayInfoId, int start, int pagingLimit);
}
