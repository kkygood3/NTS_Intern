/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.service;

import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface DisplayInfoService {
	DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId);

	DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId);
}
