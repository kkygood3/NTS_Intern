/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.dao.product.ProductDao;
import com.nts.dto.product.ProductResponse;
import com.nts.exception.ProductParamException;
import com.nts.service.product.ProductService;

/**
 * @author 전연빈
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	private static final int SELECT_PRODUCTS_ALL = 0;

	/**
	 * @desc 카테고리 별 product 가져오기
	 * @return productResponse( items [product list] , totalCount [카테고리별 총 개수] )
	 */
	@Transactional
	@Override
	public ProductResponse getProductsByCategory(int categoryId, int start) throws ProductParamException {

		if (!isValidRequest(start)) {
			throw new ProductParamException("start값이 음수입니다");
		}

		ProductResponse productResponse = new ProductResponse();

		if (categoryId != SELECT_PRODUCTS_ALL) {
			productResponse.setItems(productDao.selectProductsByCategory(categoryId, start));
			productResponse.setTotalCount(productDao.selectProductsCountByCategory(categoryId));
		} else {
			productResponse.setItems(productDao.selectProductsAll(categoryId, start));
			productResponse.setTotalCount(productDao.selectProductsCountAll());
		}

		return productResponse;
	}

	/**
	 * @desc 유효 요청 검사 ( start 기준 )
	 * @param start
	 */
	private boolean isValidRequest(int start) {
		return start >= 0;
	}

}
