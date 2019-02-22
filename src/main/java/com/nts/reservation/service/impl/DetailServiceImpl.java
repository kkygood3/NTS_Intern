package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.DetailDao;
import com.nts.reservation.dto.detail.DisplayInfo;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.product.ProductImage;
import com.nts.reservation.dto.product.ProductPrice;
import com.nts.reservation.service.DetailService;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Service
@Transactional(readOnly = true)
public class DetailServiceImpl implements DetailService {
	@Autowired
	private DetailDao detailDao;

	@Override
	public DisplayInfo getDisplayInfo(Long displayInfoId) {
		return detailDao.selectDisplayInfo(displayInfoId);
	}

	@Override
	public List<ProductImage> getProductImages(Long displayInfoId) {
		return detailDao.selectProductImages(displayInfoId);
	}

	@Override
	public DisplayInfoImage getDisplayInfoImage(Long displayInfoId) {
		return detailDao.selectDisplayInfoImage(displayInfoId);
	}

	@Override
	public Double getAverageScore(Long displayInfoId) {
		return detailDao.selectAverageScore(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(Long displayInfoId) {
		return detailDao.selectProductPrices(displayInfoId);
	}
}
