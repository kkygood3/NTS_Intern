/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.download.DownloadInfo;

public interface DownloadService {
	/**
	 * /download/{reservationInfoImageId}요청에 대응
	 * 다운로드하려는 이미지 파일 정보를 조회  
	 * @param reservationInfoImageId
	 * @return DownloadInfo
	 */
	DownloadInfo downloadImageFile(int reservationInfoImageId);
}
