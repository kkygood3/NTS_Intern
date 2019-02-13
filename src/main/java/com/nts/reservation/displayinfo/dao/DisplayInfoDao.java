/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.dao;

import com.nts.reservation.displayinfo.dto.DisplayInfo;
import com.nts.reservation.displayinfo.dto.DisplayInfoImage;

public interface DisplayInfoDao {

	DisplayInfo selectDisplayInfoByDisplayInfoId(int displayInfoId);
	DisplayInfoImage selectDisplayInfoImageByDisplayInfoId(int displayInfoId);
	double selectAverageScore(int displayInfoId);
}
