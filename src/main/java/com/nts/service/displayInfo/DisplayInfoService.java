/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.displayInfo;

import com.nts.dto.displayinfo.DisplayInfo;
import com.nts.dto.displayinfo.DisplayInfos;
import com.nts.exception.NotFoundException;

/**
 * @author 전연빈
 */
public interface DisplayInfoService {
	public DisplayInfos getDisplayInfosByDisplayInfoId(int displayInfoId) throws NotFoundException;
	public DisplayInfo  getDisplayInfoByDisplayInfoId(int displayInfoId) throws NotFoundException;
}
