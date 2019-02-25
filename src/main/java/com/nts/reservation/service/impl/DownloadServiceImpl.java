/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.download.DownloadDao;
import com.nts.reservation.dto.download.DownloadInfo;
import com.nts.reservation.service.DownloadService;

@Service
public class DownloadServiceImpl implements DownloadService {
	@Autowired
	DownloadDao downloadDao;

	@Override
	public DownloadInfo downloadImageFile(int reservationInfoImageId) {
		return downloadDao.selectDownloadInfo(reservationInfoImageId);
	}
}
