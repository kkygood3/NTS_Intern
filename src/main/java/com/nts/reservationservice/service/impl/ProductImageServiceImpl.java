/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservationservice.dao.ProductImageDao;
import com.nts.reservationservice.dto.ProductImageDto;
import com.nts.reservationservice.service.ProductImageService;

@Service
public class ProductImageServiceImpl implements ProductImageService {

	@Autowired
	ProductImageDao productImageDao;

	@Override
	@Transactional
	public ProductImageDto getProductImageByProductIdAndType(Long productId, String type) {
		return productImageDao.selectProductImageByProductIdAndType(productId, type);
	}
}
