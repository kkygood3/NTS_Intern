/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.math.BigDecimal;
import java.util.List;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.DisplayInfo;
import com.nts.reservation.dto.DisplayInfoImage;
import com.nts.reservation.dto.ProductImage;
import com.nts.reservation.dto.ProductPrice;

/**
* @author  : 이승수
*/
public interface DetailProductService {
	public BigDecimal getAverageScore(Integer displayInfoId);

	public List<Comment> getComments(Integer displayInfoId);

	public DisplayInfo getDisplayInfo(Integer displayInfoId);

	public DisplayInfoImage getDisplayInfoImage(Integer displayInfoId);

	public List<ProductImage> getProductImages(Integer displayInfoId);

	public List<ProductPrice> getProductPrices(Integer displayInfoId);
}
