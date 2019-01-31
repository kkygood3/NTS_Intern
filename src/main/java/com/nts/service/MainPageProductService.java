/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.service;

import java.util.List;

import com.nts.dto.MainPageProduct;

public interface MainPageProductService extends DefaultService {
	/**
	 * product_api_view에 Select 수행시 Paging 처리를 위해 한번에 가져올 row 수
	 * @value	4 
	 */
	public static final Integer LIMIT = 4;

	/**
	 * product_api_view를 조회
	 * @param	categoryId	해당 카테고리에 속하는 상품
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	인터페이스의 LIMIT만큼 해당 DTO의 List로 반환
	 */
	public List<MainPageProduct> getProductsByCategory(long categoryId, long start);
	
	/**
	 * product_api_view를 조회. 카테고리 구분 없음
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	인터페이스의 LIMIT만큼 해당 DTO의 List로 반환
	 */
	public List<MainPageProduct> getProducts(long start);

	/**
	 * product_api_view를 조회
	 * @param	categoryId	해당 카테고리에 속하는 상품
	 * @return	해당 View의 row 수
	 */
	public int getCountByCategory(int categoryId);

	/**
	 * product_api_view를 조회. 카테고리 구분 없음
	 * @return 해당 View의 row 수
	 */
	public int getCount();
}
