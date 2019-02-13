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
public class DisplayInfoResponseDto {
	DisplayInfoDto displayInfo;
	CommentResponseDto comments;

	public DisplayInfoResponseDto(DisplayInfoDto displayInfo, CommentResponseDto comments) {
		this.displayInfo = displayInfo;
		this.comments = comments;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}

	public CommentResponseDto getComments() {
		return comments;
	}

	public void setComments(CommentResponseDto comments) {
		this.comments = comments;
	}

}
