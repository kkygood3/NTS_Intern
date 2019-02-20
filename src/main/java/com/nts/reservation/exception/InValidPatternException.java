/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 육성렬
 */
public class InValidPatternException extends Exception{
	
	public InValidPatternException(String pattern, String value) {
		super("패턴과 일치하지 않는 값입니다. [패턴] : " + pattern + " [값] : " + value);
	}
	
}
