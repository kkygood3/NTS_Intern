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
	 * @return 인터페이스의 LIMIT만큼 해당 DTO의 List로 반환
	 */
	List<MainProduct> getProducts(int categoryId, int start, int pagingLimit);

	/**
	 * api/products response에 사용. 카테고리 구분 없음
	 * @param start	페이지에 출력할 데이터의 시작 index
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return 인터페이스의 LIMIT만큼 해당 DTO의 List로 반환
	 */
	List<MainProduct> getProducts(int start, int pagingLimit);

	/**
	 * api/products response에 사용
	 * @param categoryId	해당 카테고리에 속하는 상품
	 * @return 해당 View의 row 수
	 */
	int getProductCount(int categoryId);

	/**
	 * api/products response에 사용. 카테고리 구분 없음
	 * @return 해당 View의 row 수
	 */
	int getProductCount();

	/**
	 * api/categories response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return MainCategory List
	 */
	List<MainCategory> getCategories(int pagingLimit);

	/**
	 * api/promotions response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return default LIMIT만큼 조회하여 반환
	 */
	List<MainPromotion> getPromotions(int pagingLimit);

	/**
	 * api/promotions response에 사용
	 * @return 해당 View의 row 수
	 */
	int getPromotionCount();
}
