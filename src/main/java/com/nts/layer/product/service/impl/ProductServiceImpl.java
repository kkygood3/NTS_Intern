/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.layer.product.dao.ProductDao;
import com.nts.layer.product.dto.ProductResponse;
import com.nts.layer.product.service.ProductService;

/**
 * @author 전연빈
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	/**
	 * @desc 카테고리 별 product 가져오기
	 * @return productResponse( items [product list] , totalCount [카테고리별 총 개수] )
	 */
	@Transactional(readOnly=true)
	@Override
	public ProductResponse getProductsByCategory(String categoryId, int start) {
		
		ProductResponse productResponse = new ProductResponse();
		
		productResponse.setItems(productDao.selectProductsByCategory(categoryId, start));
		productResponse.setTotalCount(productDao.selectCountByCategory(categoryId));
		
		return productResponse;
	}

}
