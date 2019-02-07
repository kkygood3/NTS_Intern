/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.displayInfo;

import com.nts.dto.displayinfo.DisplayInfos;
import com.nts.exception.DisplayInfoNullException;

/**
 * @author 전연빈
 */
public interface DisplayInfoService {
	public DisplayInfos getDisplayInfos(int displayInfoId) throws DisplayInfoNullException;
}
