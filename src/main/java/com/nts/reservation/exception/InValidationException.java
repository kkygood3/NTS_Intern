/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

/**
 * @author jinwoo.bae
 *
 */
public class InValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InValidationException(String message) {
		super(message);
	}
}
