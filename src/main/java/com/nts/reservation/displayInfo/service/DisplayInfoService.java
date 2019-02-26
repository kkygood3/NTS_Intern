/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.service;

import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;
import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface DisplayInfoService {
	DisplayInfo getDisplayInfo(int displayInfoId);

	DisplayInfoImage getDisplayInfoImage(int displayInfoId);

	DisplayInfoResponse getDisplayInfoResponse(int displayInfoId, int start, int limit);

	DisplayInfo getDisplayInfoByReservationInfoId(int reservationInfoId);
}
