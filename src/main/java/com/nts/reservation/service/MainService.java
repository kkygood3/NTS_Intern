/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.main.MainCategory;
import com.nts.reservation.dto.main.MainProduct;
import com.nts.reservation.dto.main.MainPromotion;

public interface MainService {
	/**
	 * api/products response에 사용
	 * @param categoryId	해당 카테고리에 속하는 상품
	 * @param start	페이지에 출력할 데이터의 시작 index
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	List<MainProduct> getProducts(int categoryId, int start, int pagingLimit);

	/**
	 * api/products response에 사용
	 * @param categoryId	해당 카테고리에 속하는 상품
	 */
	int getProductCount(int categoryId);

	/**
	 * api/categories response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	List<MainCategory> getCategories(int pagingLimit);

	/**
	 * api/promotions response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	List<MainPromotion> getPromotions(int pagingLimit);

	/**
	 * api/promotions response에 사용
	 */
	int getPromotionCount();
}
