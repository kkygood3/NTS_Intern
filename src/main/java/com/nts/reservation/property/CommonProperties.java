/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.property;

public class CommonProperties {
	/**
	 * 클라이언트가 페이지에 접속할 때, 한 페이지의 크기를 명시하지 않았다면 출력할 기본 item 개수  
	 */
	public static final String CATEGORY_DEFAULT_PAGING_LIMIT = "5";
	public static final String DETAIL_DEFAULT_PAGING_LIMIT = "3";
	public static final String PRODUCT_DEFAULT_PAGING_LIMIT = "4";
	public static final String PROMOTION_DEFAULT_PAGING_LIMIT = "20";
	public static final String REVIEW_DEFAULT_PAGING_LIMIT = "10";
	public static final String MY_RESERVATION_DEFAULT_PAGING_LIMIT = "3";
	
	/**
	 * detail, review 페이지에서 comment 조회 시작 위치(start)를 명시하지 않는다면, 처음부터 조회하는 기본 값(0) 
	 */
	public static final String COMMENT_DEFAULT_START = "0";
	
	/**
	 * 정규표현 검사식
	 */
	public static final String REG_TELEPHONE = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
	public static final String REG_EMAIL = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";

	/**
	 * 프로젝트 내에서 사용하는 특정 형식의 최대 크기를 정의
	 * 이름, 이메일, 티켓 구매 수
	 */
	public static final int MAX_NAME_LENGTH = 17;
	public static final int MAX_EMAIL_LENGTH = 50;
	public static final int MAX_TICKET_COUNT = 10;

	public static final int MAX_REVIEW_SCORE = 5;
	public static final int MIN_REVIEW_SCORE = 1;
	
	public static final int MAX_COMMENT_LENGTH = 400;
	public static final int MIN_COMMENT_LENGTH = 5;


	public static final String[] ALLOW_FILE_EXTENSION = {"image/jpeg", "image/png", "image/jpg"};

}
