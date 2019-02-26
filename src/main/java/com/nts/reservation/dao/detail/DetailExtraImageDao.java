/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.detail;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.detail.DetailExtraImage;

/**
 * /api/products/{displayInfoId}/extra 요청에 대응
 */
public interface DetailExtraImageDao {
	/**
	 * detail의 swipeImage 영역에 추가할수 있는 item을 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 */
	public DetailExtraImage selectDetailExtraImage(int displayInfoId);
}
