/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.display.dao;

import com.nts.reservation.display.dto.DisplayInfo;
import com.nts.reservation.display.dto.DisplayInfoImage;

public interface DisplayDao {
	DisplayInfo selectDisplayInfo(int displayInfoId);
	DisplayInfoImage selectDisplayInfoImage(int displayInfoId);
}
