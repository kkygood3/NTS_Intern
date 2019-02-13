package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import com.nts.reservation.detail.dto.Comment;
import com.nts.reservation.detail.dto.DisplayInfo;
import com.nts.reservation.detail.dto.DisplayInfoImage;
import com.nts.reservation.detail.dto.ProductImage;
import com.nts.reservation.detail.dto.ProductPrice;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public interface DetailService {
	DisplayInfo selectDisplayInfo(Long displayInfoId);

	List<ProductImage> selectProductImages(Long displayInfoId);

	DisplayInfoImage selectDisplayInfoImage(Long displayInfoId);

	List<Comment> selectComments(Long displayInfoId);

	double selectAverageScore(Long displayInfoId);

	List<ProductPrice> selectProductPrices(Long displayInfoId);
}
