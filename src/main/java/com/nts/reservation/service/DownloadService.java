/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.download.DownloadInfo;

public interface DownloadService {
	/**
	 * 파일 
	 * @param reservationInfoImageId
	 * @return
	 */
	DownloadInfo downloadImageFile(int reservationInfoImageId);
}
