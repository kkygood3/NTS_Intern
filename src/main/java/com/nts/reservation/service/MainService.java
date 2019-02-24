/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.main.MainCategoryResponse;
import com.nts.reservation.dto.main.MainProductResponse;
import com.nts.reservation.dto.main.MainPromotionResponse;

public interface MainService {
	/**
	 * api/products response에 사용
	 * @param categoryId	해당 카테고리에 속하는 상품
	 * @param start	페이지에 출력할 데이터의 시작 index
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	MainProductResponse getProducts(int categoryId, int start, int pagingLimit);

	/**
	 * api/categories response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	MainCategoryResponse getCategories(int pagingLimit);

	/**
	 * api/promotions response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	MainPromotionResponse getPromotions(int pagingLimit);
}
