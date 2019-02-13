package com.nts.reservation.service.detail;

import java.util.List;

import com.nts.reservation.dto.detail.DetailResponse;

public interface DetailResponseService {
	
	List<DetailResponse> getDetailDisplay(int displayInfoId, int limit);
}
