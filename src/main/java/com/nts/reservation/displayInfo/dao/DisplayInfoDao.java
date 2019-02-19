/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.dao;

import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface DisplayInfoDao {
	DisplayInfo selectDisplayInfo(int displayInfoId);

	DisplayInfoImage selectDisplayInfoImage(int displayInfoId);
}
