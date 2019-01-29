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

	/**
	 * parameter의 category상태를 확인후 전체카테고리나 특정카테고리의 ProductResponse 객체반환하는 dao를 호출후 return된 객체 반환
	 * categoryId가 0일경우 isAllCategory Method 결과값 true, 그외 false
	 */
	@Override
	public ProductResponse getProductResponse(ProductRequest productRequest) {

		if (productRequest.isAllCategory()) {
			return productDao.getAllCategoryProductResponse(productRequest);
		} else {
			return productDao.getOneCategoryProductResponse(productRequest);
		}

	}

}
