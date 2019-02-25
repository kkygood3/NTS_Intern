/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.service;

import java.util.List;

import com.nts.reservation.productprice.model.ProductPrice;

public interface ProductPriceService {

	List<ProductPrice> getProductPriceListByDisplayInfoId(int displayInfoId);

}
