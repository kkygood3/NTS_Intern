/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.common.annotation.IsEmpty;
import com.nts.reservation.productprice.dao.ProductPriceDao;
import com.nts.reservation.productprice.model.ProductPrice;
import com.nts.reservation.productprice.service.ProductPriceService;

@Service
public class ProductPriceServiceLogic implements ProductPriceService {

	private final ProductPriceDao productPriceDao;

	@Autowired
	public ProductPriceServiceLogic(ProductPriceDao productPriceDao) {
		this.productPriceDao = productPriceDao;
	}

	/**
	 * 상품의 가격정보 조회
	 */
	@Override
	@IsEmpty
	public List<ProductPrice> getProductPriceListByDisplayInfoId(int displayInfoId) {
		return productPriceDao.selectProductPriceListByDisplayInfoId(displayInfoId);
	}

}
