/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dao;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;

public interface CommentDao {
	List<CommentImage> selectCommentImages(int commentId);

	List<Comment> selectCommentByLimit(int displayInfoId, int limit);
	List<Comment> selectComment(int displayInfoId);
}
