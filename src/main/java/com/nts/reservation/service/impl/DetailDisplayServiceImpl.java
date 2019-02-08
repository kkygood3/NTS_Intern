package com.nts.reservation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.reservation.dao.detail.DetailDisplayDao;
import com.nts.reservation.dao.detail.DisplayInfoDao;
import com.nts.reservation.dao.detail.DisplayInfoImageDao;
import com.nts.reservation.dao.detail.ProductImageDao;
import com.nts.reservation.dao.detail.ProductPriceDao;
import com.nts.reservation.dto.detail.DetailDisplay;
import com.nts.reservation.service.DetailDisplayService;

public class DetailDisplayServiceImpl implements DetailDisplayService {

	@Autowired
	private DisplayInfoDao displayInfoDao;
	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;
	@Autowired
	private ProductImageDao productImageDao;
	@Autowired
	private ProductPriceDao productPriceDao;

	@Override
	public DetailDisplay getDetailDisplay(int displayInfoId) {
		return null;
	}

}
