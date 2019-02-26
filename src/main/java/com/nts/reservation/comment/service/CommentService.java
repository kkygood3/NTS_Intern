/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service;

import com.nts.reservation.comment.dto.CommentParam;
import com.nts.reservation.comment.dto.CommentResponse;

public interface CommentService {

	CommentResponse getComments(int displayInfoId, int limit);

	int insertComment(CommentParam commentParam);
}
