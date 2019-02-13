/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.ProductDao;
import com.nts.reservation.dto.ProductDto;
import com.nts.reservation.service.ProductService;

/**
 * @author 육성렬
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	/**
	 * @desc 카테고리의 구별없이 start 매개변수에서부터 LIMIT개만큼 상품 정보를 반환한다.
	 * @param start
	 */
	@Override
	public List<ProductDto> getProductList(int start) {
		List<ProductDto> result = productDao.selectProduct(start, ProductService.LIMIT);
		return result;
	}

	/**
	 * @desc 요청하는 카테고리 id에 속하는 카테고리에서 start 매개변수에서부터 LIMIT개만큼 상품 정보를 반환한다.
	 * @param start
	 * @param categoryId
	 */
	@Override
	public List<ProductDto> getProductList(int start, int categoryId) {
		List<ProductDto> result = productDao.selectProductByCategory(start, ProductService.LIMIT, categoryId);
		return result;
	}

	/**
	 * @desc 모든 상품들의 갯수를 반환한다.
	 */
	@Override
	public int getCount() {
		return productDao.countAll();
	}

	/**
	 * @desc category Id에 해당하는 카테고리에 속한 상품들의 갯수를 반환한다.
	 * @param categoryId
	 */
	@Override
	public int getCount(int categoryId) {
		return productDao.countByCategory(categoryId);
	}

	/**
	 * @desc 프로모션이 진행되고 있는 상품들을 반환한다.
	 */
	@Override
	public List<ProductDto> getPromotionProductList() {
		return productDao.selectPromotionProduct();
	}
}
