/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.property;

public class Properties {
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
	 * /api/products 요청시 categoryId를 명시하지 않는다면, 카테고리 구별없이 모두 출력하는 기본 값(0)
	 */
	public static final String PRODUCT_DEFAULT_CATEGORY_ID = "0";

	/**
	 * /api/products 요청시 시작 위치(start)를 명시하지 않는다면, 처음부터 조회하는 기본 값(0) 
	 */
	public static final String PRODUCT_DEFAULT_START = "0";

	/**
	 * detail, review 페이지에서 comment 조회 시작 위치(start)를 명시하지 않는다면, 처음부터 조회하는 기본 값(0) 
	 */
	public static final String COMMENT_DEFAULT_START = "0";

	/**
	 * 정규표현 검사식
	 */
	public static final String REG_TELEPHONE = "^\\d{2,3}-\\d{3,4}-\\d{4}$";
	public static final String REG_EMAIL = "^(?=.{1,50}$)[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
	public static final String REG_NAME  = "^(?=.{1,17}$).+$";
	public static final String REG_COMMENT  = "^(?=.{5,400}$).+$";
	
	
	/**
	 * 프로젝트 내에서 사용하는 특정 형식의 최대 크기를 정의
	 * 이름, 이메일, 티켓 구매 수
	 */
	public static final int MAX_TICKET_COUNT = 10;

	public static final int MAX_REVIEW_SCORE = 5;
	public static final int MIN_REVIEW_SCORE = 1;

	/**
	 * 이미지 파일 업로드/다운로드에 사용.
	 */
	public static final String[] ALLOW_FILE_EXTENSION = {"image/jpeg", "image/png", "image/jpg"};

	public static final String ROOT_DIR_COMMNET_IMAGE = "c:/tmp/img_map/";

	public static final int RANDOM_SUFFIX_LENGTH = 10;
	
	/**
	 * 시스템 이용중에 문제가 있었을 때 출력할 view
	 */
	public static final String ERROR_VIEW = "error/invalidAccess";
	
	/**
	 * 캐시 period
	 * 31556926 seconds = 1 year
	 * @value 31556926
	 */
	public static final int DEFAULT_CACHE_PERIOD = 31556926;

}
