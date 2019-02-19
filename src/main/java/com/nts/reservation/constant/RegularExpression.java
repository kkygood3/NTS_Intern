/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.constant;

/**
 * 정규식
 * @author jinwoo.bae
 */
public class RegularExpression {
	public static final String NAME_REGEXP = "^[가-힣|a-z|A-Z]+$";
	public static final String PHONE_REGEXP = "^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$";
	public static final String EMAIL_REGEXP = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
}
