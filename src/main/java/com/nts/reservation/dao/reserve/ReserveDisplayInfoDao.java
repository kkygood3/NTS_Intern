/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reserve;

import com.nts.reservation.dto.reserve.ReserveDisplayInfo;

/**
 * reserve 페이지 로드시 사용하는 상품 전시 정보
 */
public interface ReserveDisplayInfoDao {
	/**
	 * reserve 페이지를 로드할 때 DisplayInfo 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	ReserveDisplayInfo selectReviewDisplayInfo(int displayInfoId);
}
