package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DatailPageInfoDao;
import com.nts.reservation.dto.DatailPageInfo;
import com.nts.reservation.service.DetailPageInfoService;

@Service
public class DatailPageInfoServiceImpl implements DetailPageInfoService {
	@Autowired
	DatailPageInfoDao datailPageInfoDao;

	@Override
	@Transactional
	public DatailPageInfo getDetailPageInfo(long displayInfoId) {
		return datailPageInfoDao.selectDetailPageInfo(displayInfoId);
	}
}
