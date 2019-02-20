/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

/**
 * @author 육성렬
 */
public class ErrorMessageDto {
	private String errorMessage;

	public ErrorMessageDto(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorMessageDto [errorMessage=" + errorMessage + "]";
	}

}
