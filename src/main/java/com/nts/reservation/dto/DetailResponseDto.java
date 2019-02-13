/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

/**
 * 전시상품 Response용 DTO
 * 
 * @author jinwoo.bae
 */
public class DetailResponseDto {
	DisplayInfoDto displayInfo;
	CommentResponseDto commentResponse;

	public DetailResponseDto(DisplayInfoDto displayInfo, CommentResponseDto commentResponse) {
		this.displayInfo = displayInfo;
		this.commentResponse = commentResponse;
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

}
