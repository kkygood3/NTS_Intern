/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.service;

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
		List<ProductDto> list = productDao.selectAllPaging(start, SELECT_LIMIT);
		return list;
	}

	@Override
	public List<ProductDto> getProductsByCategoryId(int categoryId, int start) {
		List<ProductDto> list = productDao.selectByCategoryIdPaging(categoryId, start, SELECT_LIMIT);
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
