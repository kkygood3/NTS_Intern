package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import com.nts.reservation.dto.common.DisplayInfo;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.detail.ProductImage;
import com.nts.reservation.dto.detail.ProductPrice;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public interface DetailService {
	DisplayInfo getDisplayInfo(Long displayInfoId);

	List<ProductImage> getProductImages(Long displayInfoId);

	DisplayInfoImage getDisplayInfoImage(Long displayInfoId);

	Double getAverageScore(Long displayInfoId);

	List<ProductPrice> getProductPrices(Long displayInfoId);
}
