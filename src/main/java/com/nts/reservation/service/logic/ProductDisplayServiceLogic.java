/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDisplayDao;
import com.nts.reservation.model.ProductDisplay;
import com.nts.reservation.service.ProductDisplayService;

@Service
public class ProductDisplayServiceLogic implements ProductDisplayService {

	@Autowired
	private ProductDisplayDao productDisplayDao;

	/**
	 * 특정 displayInfo 정보와 관련 이미지 url 목록을 dao에서 받아 조합하여 반환 
	 */
	@Override
	public ProductDisplay getProductDisplay(int displayInfoId) {
		ProductDisplay productDisplay = productDisplayDao.getProductDisplay(displayInfoId);
		List<String> prodcutDisplayImageUrList = productDisplayDao.getProductDisplayImageUrlList(displayInfoId);

		productDisplay.setProductDisplayImageUrlList(prodcutDisplayImageUrList);
		return productDisplay;
	}

}
