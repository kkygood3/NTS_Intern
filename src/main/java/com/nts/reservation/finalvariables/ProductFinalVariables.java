/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.finalvariables;

public class ProductFinalVariables {
	/**
	 * /api/products 요청시 categoryId를 명시하지 않는다면, 카테고리 구별없이 모두 출력하는 기본 값(0)
	 */
	public static final String PRODUCT_DEFAULT_CATEGORY_ID = "0";

	/**
	 * /api/products 요청시 시작 위치(start)를 명시하지 않는다면, 처음부터 조회하는 기본 값(0) 
	 */
	public static final String PRODUCT_DEFAULT_START = "0";
}
