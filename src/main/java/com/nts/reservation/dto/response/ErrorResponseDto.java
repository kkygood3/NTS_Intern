/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

/**
 * 에러 response용 Dto
 * @author jinwoo.bae
 */
public class ErrorResponseDto {
	private Boolean isError;
	private String errorMsg;

	public ErrorResponseDto(Boolean isError, String errorMsg) {
		this.isError = isError;
		this.errorMsg = errorMsg;
	}

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
