/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.reserve.ReservePrice;

/**
 * reserve 페이지 로드시 사용하는 상품 가격 정보
 */
public interface ReservePriceDao {
	/**
	 * reserve 페이지를 로드할 때 상품의 가격 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public List<ReservePrice> selectReservePrice(
		@Param("displayInfoId") int displayInfoId);
}
