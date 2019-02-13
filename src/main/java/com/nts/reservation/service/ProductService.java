/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.CommentResponseDto;
import com.nts.reservation.dto.DetailResponseDto;
import com.nts.reservation.dto.ProductResponseDto;

/**
 * 상품에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ProductService {
	ProductResponseDto getProductResponse(int categoryId, int start, int limit);

	DetailResponseDto getDetailResponse(int productId, int displayInfoId, int commentLimit);

	CommentResponseDto getCommentResponse(int productId, int start, int limit);
}
