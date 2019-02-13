/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface CommentDao {
	List<Comment> selectCommentByProductId(int productId);

	double selectAverageScoreByProductId(int productId);
}
