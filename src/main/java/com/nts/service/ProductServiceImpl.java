/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.dao.ProductDao;
import com.nts.dto.ProductDto;

/**
 * 상품 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao productDao;

	@Override
	public List<ProductDto> getProducts(int start) {
		List<ProductDto> list = productDao.selectAll(start, ProductService.LIMIT);
		return list;
	}

	@Override
	public List<ProductDto> getProductsByCategoryId(int categoryId, int start) {
		List<ProductDto> list = productDao.selectByCategoryId(categoryId, start, ProductService.LIMIT);
		return list;
	}

	@Override
	public int getCount() {
		return productDao.selectCount();
	}

	@Override
	public int getCountByCategoryId(int categoryId) {
		return productDao.selectCountByCategoryId(categoryId);
	}

}
