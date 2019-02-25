/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.main.MainProduct;

/**
 * /api/products 요청에 대응
 */
public interface MainProductDao {
	/**
	 * main 페이지를 로드할 때 필요한 product정보 조회
	 * @param categoryId - 해당 카테고리에 속하는 product로 한정
	 * @param start - 한 페이지에 출력할 item 개수
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<MainProduct> selectPagingProducts(
		@Param("categoryId") int categoryId,
		@Param("start") int start,
		@Param("pagingLimit") int pagingLimit);

	/**
	 * main 페이지를 로드할 때 필요한 product 갯수 조회
	 * @param categoryId - 해당 카테고리에 속하는 product로 한정
	 */
	public int selectCount(
		@Param("categoryId") int categoryId);
}
