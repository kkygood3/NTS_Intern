/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.main;

import java.util.List;

import com.nts.reservation.dto.main.MainCategory;

public interface MainCategoryService {
	/**
	 * api/categories response에 사용
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 * @return MainCategory List
	 */
	List<MainCategory> getCategories(int pagingLimit);
}
