/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.service;

import com.nts.resevation.dto.CommentResponseDto;
import com.nts.resevation.dto.DisplayInfoResponseDto;
import com.nts.resevation.dto.ProductResponseDto;

/**
 * 상품에 대한 서비스 인터페이스
 * @author jinwoo.bae
 */
public interface ProductService {
	int CATEGORY_TYPE_ALL = 0;

	ProductResponseDto getProductResponse(int categoryId, int start, int limit);

	DisplayInfoResponseDto getDisplayInfoResponse(int displayInfoId, int productImageLimit);

	CommentResponseDto getCommentResponse(int productId, int start, int limit);
}
