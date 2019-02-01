/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.project3.dao.ProductDao;
import com.nts.project3.dto.ProductDto;

/**
 * 상품 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductDto> getProducts(int start) {
		List<ProductDto> getProducts = Collections.<ProductDto>emptyList();
		int count = productDao.selectCount();
		if (count > 0) {
			getProducts = productDao.selectAllPaging(start, SELECT_LIMIT);
		}
		return getProducts;
	}

	@Override
	public List<ProductDto> getProductsByCategoryId(int categoryId, int start) {
		List<ProductDto> products = Collections.<ProductDto>emptyList();
		int count = productDao.selectCountByCategoryId(categoryId);
		if (count > 0) {
			products = productDao.selectByCategoryIdPaging(categoryId, start, SELECT_LIMIT);
		}
		return products;
	}
}
