package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import com.nts.reservation.dto.common.DisplayInfo;
import com.nts.reservation.dto.detail.Comment;
import com.nts.reservation.dto.detail.DisplayInfoImage;
import com.nts.reservation.dto.detail.ProductImage;
import com.nts.reservation.dto.detail.ProductPrice;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public interface DetailService {
	DisplayInfo selectDisplayInfo(Long displayInfoId);

	List<ProductImage> selectProductImages(Long displayInfoId);

	DisplayInfoImage selectDisplayInfoImage(Long displayInfoId);

	List<Comment> selectComments(Long displayInfoId);

	Double selectAverageScore(Long displayInfoId);

	List<ProductPrice> selectProductPrices(Long displayInfoId);
}
