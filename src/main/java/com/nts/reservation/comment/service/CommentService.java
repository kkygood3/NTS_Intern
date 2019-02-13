/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface CommentService {
	List<Comment> getCommentByProductId(int productId);

	double getAverageScoreByProductId(int productId);
}
