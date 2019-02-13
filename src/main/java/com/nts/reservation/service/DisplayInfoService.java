/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.CommentDto;
import com.nts.reservation.dto.DisplayInfoDto;
import com.nts.reservation.dto.DisplayInfoImageDto;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.ProductPriceDto;
import com.nts.reservation.dto.ProductPriceForClientDto;

/**
 * @author 육성렬
 */
public interface DisplayInfoService {
	public List<CommentDto> getCommentList(Long displayInfoId);

	public DisplayInfoDto getDisplayInfo(Long displayInfoId);

	public DisplayInfoImageDto getDisplayInfoImage(Long displayInfoId);

	public List<ProductImageDto> getProductImageList(Long productId);

	public List<ProductPriceDto> getProductPriceList(Long productId);

	public List<ProductPriceForClientDto> getProductPriceForClietList(Long productId);

	public float getCommentAvgScore(Long displayInfoId);

	public ProductImageDto getProductMainImage(Long productId);
}
