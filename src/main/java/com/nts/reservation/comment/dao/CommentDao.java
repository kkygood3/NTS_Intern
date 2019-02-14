/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;

public interface CommentDao {
	List<Comment> selectCommentBydisplayInfoId(int displayInfoId, int limit);

	List<CommentImage> selectCommentImage(int displayInfoId, int commentId);

	double selectCommentAvgScore(int displayInfoId);
}
