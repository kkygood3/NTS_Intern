/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.resevation.dao.ProductDao;
import com.nts.resevation.dto.ProductDto;
import com.nts.resevation.dto.ProductResponseDto;

/**
 * 상품 서비스 인터페이스 구현로직
 * @author jinwoo.bae
 */
@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponseDto getProductResponse(int start) {
		List<ProductDto> products = Collections.<ProductDto>emptyList();
		int count = productDao.selectCount();
		if (count > 0) {
			products = productDao.selectAllPaging(start, SELECT_LIMIT);
		}
		return new ProductResponseDto(products, count);
	}

	@Override
	public ProductResponseDto getProductResponse(int categoryId, int start) {
		List<ProductDto> products = Collections.<ProductDto>emptyList();
		int count = productDao.selectCountByCategoryId(categoryId);
		if (count > 0) {
			products = productDao.selectByCategoryIdPaging(categoryId, start, SELECT_LIMIT);
		}
		return new ProductResponseDto(products, count);
	}
}
