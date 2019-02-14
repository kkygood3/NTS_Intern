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
	List<Comment> selectAllCommentByDisplayInfoId(int displayInfoId);

	List<Comment> selectLimitCommentByDisplayInfoId(int displayInfoId, int start, int limit);

	List<CommentImage> selectCommentImageByCommentId(int commentId);

	double selectAverageScoreByDisplayInfoId(int displayInfoId);
}
