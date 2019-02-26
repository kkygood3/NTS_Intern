/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.controller.advice.message;

/**
 * @author 육성렬
 */
public class ErrorMessage {
	public static final String INTERNAL_ERROR_MSG = "서버 내부 오류가 발생했습니다.";
	public static final String BAD_REQUEST_ERROR_MSG = "잘못 된 요청을 보냈습니다.";
	public static final String COMMON_ERROR_MSG = "에러가 발생하였습니다.";
	public static final String UNAUTHORIZED_ERROR_MSG = "권한이 없는 요청입니다.";
	public static final String NOT_FOUND_REQUEST = "해당 요청을 찾을 수 없습니다.";
}
