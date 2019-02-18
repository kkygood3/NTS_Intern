/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.property;


public class CommonProperties {
	/**
	 * 클라이언트가 페이지에 접속할 때, 한 페이지의 크기를 명시하지 않았다면 출력할 기본 item 개수  
	 */
	public final static String CATEGORY_DEFAULT_PAGING_LIMIT = "5";
	public final static String DETAIL_DEFAULT_PAGING_LIMIT = "3";
	public static final String PRODUCT_DEFAULT_PAGING_LIMIT = "4";
	public static final String PROMOTION_DEFAULT_PAGING_LIMIT = "20";
	public static final String REVIEW_DEFAULT_PAGING_LIMIT = "10";

	/**
	 * detail, review 페이지에서 comment 조회 시작 위치(start)를 명시하지 않는다면, 처음부터 조회하는 기본 값(0) 
	 */
	public static final String COMMENT_DEFAULT_START = "0";
	
	/**
	 * 정규표현 검사식
	 */
	public static final String REG_TELEPHONE = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
	public static final String REG_EMAIL = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
	
	public static final int MAX_NAME_LENGTH = 17;
	public static final int MAX_EMAIL_LENGTH = 50;
	
}
