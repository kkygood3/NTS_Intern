/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.service;

import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

public interface CommentWriteService {
	void writeReview(CommentWriteRequest commentWriteRequest);
}
