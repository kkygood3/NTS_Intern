/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.service;

import com.nts.reservation.displayinfo.dto.DisplayInfoResponse;

public interface DisplayInfoService {
	DisplayInfoResponse getDisplayInfos(int displayInfoId, int limit);
}
