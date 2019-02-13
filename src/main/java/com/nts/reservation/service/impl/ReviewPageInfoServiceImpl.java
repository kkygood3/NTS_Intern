package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ReviewPageInfoDao;
import com.nts.reservation.dto.ReviewPageInfo;
import com.nts.reservation.service.ReviewPageInfoService;

@Service
public class ReviewPageInfoServiceImpl implements ReviewPageInfoService {
	@Autowired
	ReviewPageInfoDao reviewPageInfoDao;

	@Override
	@Transactional
	public ReviewPageInfo getReviewPageInfo(long displayInfoId) {
		return reviewPageInfoDao.selectByDisplayInfoId(displayInfoId);
	}
}
