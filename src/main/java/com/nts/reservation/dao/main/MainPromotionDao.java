/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.main;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.main.MainPromotion;

/**
 * /api/promotions 요청에 대응
 */
public interface MainPromotionDao {
	/**
	 * main 페이지를 로드할 때 필요한 promotion정보 조회
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<MainPromotion> selectPromotions(
		@Param("pagingLimit") int pagingLimit);

	/**
	 * main 페이지를 로드할 때 필요한 promotion 갯수 조회
	 */
	public int selectCount();
}
