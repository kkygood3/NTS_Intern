/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.detail.DetailExtraImage;
import com.nts.reservation.dto.detail.DetailResponse;

public interface DetailService {
	/**
	 * api/products/{diplayInfoId} response에 사용
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param start	페이지에 출력할 데이터의 시작 index
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	DetailResponse getDetailResponse(int displayInfoId, int start, int pagingLimit);

	/**
	 * api/products/{diplayInfoId}/extra response에 사용
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @return 해당 ID에 속하는 'et' 타입 파일이 있을때의 첫 번째
	 */
	DetailExtraImage getExtraImage(int displayInfoId);
}
