/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service;

import java.util.List;

import com.nts.reservationservice.dto.DisplayInfoDto;

public interface DisplayInfoService {
	public List<DisplayInfoDto> getDisplayInfoByProductId(Long productId);
}
