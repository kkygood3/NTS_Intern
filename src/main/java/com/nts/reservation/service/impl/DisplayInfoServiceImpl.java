package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DisplayInfoDao;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService{
	@Autowired
	DisplayInfoDao displayInfoDao;

	@Override
	@Transactional
	public DisplayInfo getDisplayInfo(long displayInfoId) {
		return displayInfoDao.selectById(displayInfoId);
	}
}
