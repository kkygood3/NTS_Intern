/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.model.ProductRequest;
import com.nts.reservation.model.ProductResponse;
import com.nts.reservation.service.ProductService;

@Service
public class ProductServiceLogic implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProductResponse(ProductRequest productRequest) {

		if (productRequest.getCategoryId() == 0) {
			return productDao.getAllCategoryProductResponse(productRequest);
		} else {
			return productDao.getOneCategoryProductResponse(productRequest);
		}

	}

}
