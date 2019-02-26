/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.reviewwrite;

import com.nts.reservation.dto.reviewwrite.ReviewWriteRequest;
import com.nts.reservation.dto.reviewwrite.ReviewWriteResponse;

public interface ReviewWrtieDao {
	/**
	 * reviewWrite 페이지 로드 reponse에 사용
	 * @param reservationInfoId
	 */
	ReviewWriteResponse selectReviewWrite(int reservationInfoId);

	int insertComment(ReviewWriteRequest reviewWriteRequest);

	int insertFileInfo(ReviewWriteRequest reviewWriteRequest);

	int insertCommentImage(ReviewWriteRequest reviewWriteRequest);
}
