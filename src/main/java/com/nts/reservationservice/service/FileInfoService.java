/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service;

import com.nts.reservationservice.dto.FileInfoDto;

public interface FileInfoService {
	public FileInfoDto getFileInfoById(Long id);
}
