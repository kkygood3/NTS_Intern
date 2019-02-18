/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface CommentDao {
	List<Comment> selectAllComment(int displayInfoId);

	List<Comment> selectLimitComment(int displayInfoId, int start, int limit);

	List<CommentImage> selectCommentImage(int commentId);

	double selectAverageScore(int displayInfoId);
}
