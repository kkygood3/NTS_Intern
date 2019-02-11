/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

import com.nts.dao.productdao.ProductDao;
import com.nts.dto.productdto.Product;
import com.nts.dto.productdto.ProductImage;
import com.nts.dto.productdto.ProductPrice;
import com.nts.exception.ValidationException;
import com.nts.service.ProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @description : Product Service
 * @package : com.nts.service.impl
 * @filename : ProductServiceImpl.java
 * @author : 최석현
 * 
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	/**
	 * @description : categoryId와 start 값을 검증 후 Dao로부터 List를 받음
	 * @throws : ValidationException
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Product> getItems(int categoryId, int start) throws ValidationException {

		if (categoryId < 0) {

			throw new ValidationException("categoryId : " + categoryId);

		} else if (start < 0) {

			throw new ValidationException("start : " + start);

		} else if (categoryId == 0) {

			return productDao.selectProductsAll(start);

		}

		return productDao.selectProductsByCategoryId(categoryId, start);

	}

	@Override
	@Transactional(readOnly = true)
	public int getCount(int categoryId) throws ValidationException {

		if (categoryId < 0) {

			throw new ValidationException("categoryId : " + categoryId);

		} else if (categoryId == 0) {

			return productDao.selectProductCountAll();

		}

		return productDao.selectProductCountByCategoryId(categoryId);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductImage> getProductImagesByDisplayInfoId(int displayInfoId) throws ValidationException {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return productDao.selectProductImagesByDisplayInfoId(displayInfoId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductPrice> getProductPricesByDisplayInfoId(int displayInfoId) {
		if (displayInfoId < 0) {
			throw new ValidationException("displayInfoId : " + displayInfoId);
		}
		return productDao.selectProductPricesByDisplayInfoId(displayInfoId);
	}

}
