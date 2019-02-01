package com.nts.reservation.service;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.util.List;

import com.nts.reservation.detail.dto.Comment;
import com.nts.reservation.detail.dto.CommentImage;
import com.nts.reservation.detail.dto.DisplayInfo;
import com.nts.reservation.detail.dto.DisplayInfoImage;
import com.nts.reservation.detail.dto.ProductImage;
import com.nts.reservation.detail.dto.ProductPrice;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */
public interface DetailService {
	public abstract DisplayInfo getDisplayInfo(Long displayInfoId);

	public abstract List<ProductImage> getProductImages(Long displayInfoId);

	public abstract DisplayInfoImage getDisplayInfoImage(Long displayInfoId);

	public abstract List<Comment> getComments(Long displayInfoId);

	public abstract List<CommentImage> getCommentsImages(Long displayInfoId);

	public abstract Double getAverageScore(Long displayInfoId);

	public List<ProductPrice> getProductPrices(Long displayInfoId);

}
