/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.main;

import java.util.List;

import com.nts.reservation.dto.main.MainProduct;

public interface MainProductService {
	/**
	 * api/products response에 사용
	 * @param	categoryId	해당 카테고리에 속하는 상품
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	인터페이스의 LIMIT만큼 해당 DTO의 List로 반환
	 */
	List<MainProduct> getProducts(long categoryId, long start,int pagingLimit);

	/**
	 * api/products response에 사용. 카테고리 구분 없음
	 * @param	start	페이지에 출력할 데이터의 시작 index
	 * @return	인터페이스의 LIMIT만큼 해당 DTO의 List로 반환
	 */
	List<MainProduct> getProducts(long start, int pagingLimit);

	/**
	 * api/products response에 사용
	 * @param	categoryId	해당 카테고리에 속하는 상품
	 * @return	해당 View의 row 수
	 */
	int getCount(int categoryId);

	/**
	 * api/products response에 사용. 카테고리 구분 없음
	 * @return 해당 View의 row 수
	 */
	int getCount();
}
