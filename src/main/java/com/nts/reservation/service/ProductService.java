/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.constant.ImageType;
import com.nts.reservation.dto.ProductImageDto;
import com.nts.reservation.dto.response.CommentResponseDto;
import com.nts.reservation.dto.response.DetailResponseDto;
import com.nts.reservation.dto.response.ProductResponseDto;
import com.nts.reservation.dto.response.ReserveResponseDto;

/**
 * 상품에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ProductService {
	ProductResponseDto getProductResponse(int categoryId, int start, int limit);

	ProductImageDto getProductImage(int productId, ImageType type);

	DetailResponseDto getDetailResponse(int productId, int displayInfoId, ImageType type, int commentLimit);

	CommentResponseDto getCommentResponse(int productId, int start, int limit);

	ReserveResponseDto getReserveResponse(int productId, int displayInfoId, ImageType type);

}
