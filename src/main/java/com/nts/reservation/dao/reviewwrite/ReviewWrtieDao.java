/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reviewwrite;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.reviewwrite.ReviewWriteResponse;

/**
 * reviewWrite 페이지에서 이용
 */
public interface ReviewWrtieDao {

	public ReviewWriteResponse selectReviewWrite(
		@Param("reservationInfoId") int reservationInfoId);
}
