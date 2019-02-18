package com.nts.reservation.display.service;

import com.nts.reservation.display.dto.DisplayResponse;

public interface DisplayService {

	DisplayResponse getDisplayInfo(int displayInfoId, int limit);
}
