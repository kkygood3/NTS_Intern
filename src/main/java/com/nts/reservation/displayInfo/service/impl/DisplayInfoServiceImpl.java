/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.displayInfo.dao.impl.DisplayInfoDaoImpl;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;
import com.nts.reservation.displayInfo.service.DisplayInfoService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {
	@Autowired
	private DisplayInfoDaoImpl displayInfoDaoImpl;

	@Override
	public DisplayInfo getDisplayInfoByDisplayInfoId(int displayInfoId) {
		return displayInfoDaoImpl.selectDisplayInfoByDisplayInfoId(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImageByDisplayInfoId(int displayInfoId) {
		return displayInfoDaoImpl.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
	}
}
