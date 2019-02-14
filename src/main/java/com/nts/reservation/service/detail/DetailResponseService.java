package com.nts.reservation.service.detail;

import java.util.List;

import com.nts.reservation.dto.detail.DetailResponse;

public interface DetailResponseService {
	/**
	 * api/products/{diplayInfoId} response에 사용
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return displayInfoId에 속하는 displayInfo, commentList
	 */
	List<DetailResponse> getDetailResponse(int displayInfoId, int pagingLimit);
}
