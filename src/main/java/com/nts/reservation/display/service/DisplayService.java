package com.nts.reservation.display.service;

import com.nts.reservation.display.dto.DisplayResponse;

public interface DisplayService {

	/**
	 * display의 정보와 limit만큼의 comment를 조회하여 가져옴 
	 * @param displayInfoId 
	 * @param limit 가져올 Comment의 limit value
	 * @return display의 정보 및 comment
	 */
	DisplayResponse getDisplayInfo(int displayInfoId, int limit);
}
