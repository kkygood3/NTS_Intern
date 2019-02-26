/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.dao;

import com.nts.reservation.commentwrite.dto.ReviewWriteRequest;

public interface CommentWriteDao {
	int insertComment(ReviewWriteRequest reviewWriteRequest);
}
