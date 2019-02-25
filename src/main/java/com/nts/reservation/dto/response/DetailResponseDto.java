/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import com.nts.reservation.dto.DisplayInfoDto;

/**
 * 전시상품 Response용 DTO
 * @author jinwoo.bae
 */
public class DetailResponseDto {
	private DisplayInfoDto displayInfo;
	private CommentResponseDto commentResponse;
	private String productImageUrl;

	public DetailResponseDto(DisplayInfoDto displayInfo, CommentResponseDto commentResponse, String productImageUrl) {
		this.displayInfo = displayInfo;
		this.commentResponse = commentResponse;
		this.productImageUrl = productImageUrl;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}

	public CommentResponseDto getCommentResponse() {
		return commentResponse;
	}

	public void setCommentResponse(CommentResponseDto commentResponse) {
		this.commentResponse = commentResponse;
	}

	public String getProductImageUrl() {
		return productImageUrl;
	}

	public void setProductImageUrl(String productImageUrl) {
		this.productImageUrl = productImageUrl;
	}

}