/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.review;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.review.ReviewDisplayInfo;

/**
 * /api/products/{displayInfoId}/review 요청에 대응
 */
public interface ReviewDisplayInfoDao {
	/**
	 * review 페이지를 로드할 때 DisplayInfo 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public ReviewDisplayInfo selectDetailDisplayInfo(int displayInfoId);
}
