/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

/**
 * @author 육성렬
 */
public class InvalidParamException extends Exception {

	private static final long serialVersionUID = 2521734074089112290L;

	public InvalidParamException(String name, Object value) {
		super("유효하지 않은 파라미터입니다. [name] : " + name + " [값] : " + value);
	}

}
