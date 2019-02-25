/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reviewwrite;

import org.apache.ibatis.annotations.Param;

import com.nts.reservation.dto.reviewwrite.ReviewWriteRequest;
import com.nts.reservation.dto.reviewwrite.ReviewWriteResponse;


public interface ReviewWrtieDao {
	/**
	 * reviewWrite 페이지 로드 reponse에 사용
	 * @param reservationInfoId
	 */
	public ReviewWriteResponse selectReviewWrite(
		@Param("reservationInfoId") int reservationInfoId);

	public int insertComment(ReviewWriteRequest reviewWriteRequest);
	public int insertFileInfo(ReviewWriteRequest reviewWriteRequest);
	public int insertCommentImage(ReviewWriteRequest reviewWriteRequest);
}
