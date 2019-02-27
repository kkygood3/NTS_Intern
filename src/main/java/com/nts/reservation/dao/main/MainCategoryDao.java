/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.List;

import com.nts.reservation.dto.main.MainCategory;

/**
 * /api/categories 요청에 대응
 */
public interface MainCategoryDao {
	/**
	 * main 페이지를 로드할 때 필요한 category정보 조회
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	List<MainCategory> selectCategories(int pagingLimit);
}
