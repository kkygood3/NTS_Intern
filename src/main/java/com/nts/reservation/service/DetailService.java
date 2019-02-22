package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.detail.DisplayInfo;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.product.ProductImage;
import com.nts.reservation.dto.product.ProductPrice;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public interface DetailService {
	DisplayInfo getDisplayInfo(Long displayInfoId);

	List<ProductImage> getProductImages(Long displayInfoId);

	DisplayInfoImage getDisplayInfoImage(Long displayInfoId);

	Double getAverageScore(Long displayInfoId);

	List<ProductPrice> getProductPrices(Long displayInfoId);
}
