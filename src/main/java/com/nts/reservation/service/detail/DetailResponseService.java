package com.nts.reservation.service.detail;

import java.util.List;

import com.nts.reservation.dto.detail.DetailResponse;

public interface DetailResponseService {
	/**
	 * api/products/{diplayInfoId} response에 사용
	 * @return displayInfoId에 속하는 displayInfo, commentList
	 */
	List<DetailResponse> getDetailDisplay(int displayInfoId, int limit);
}
