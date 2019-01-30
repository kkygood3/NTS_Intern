/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service.impl;

import com.nts.dao.ProductDao;
import com.nts.dto.Product;
import com.nts.dto.ProductResponse;
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
	ProductDao productDao;
	
	@Override
	@Transactional
	public List<Product> getItems(int categoryId, int start) {
		return productDao.selectPagingById(categoryId, start);
	}

	@Override
	@Transactional
	public int getCount(int categoryId) {
		return productDao.selectCount(categoryId);
	}
}
