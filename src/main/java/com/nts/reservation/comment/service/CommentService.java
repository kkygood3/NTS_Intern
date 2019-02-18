/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public interface CommentService {
	List<Comment> getAllComment(int displayInfoId);

	List<Comment> getLimitComment(int displayInfoId, int start, int limit);

	List<CommentImage> getCommentImage(int commentId);

	double getAverageScore(int displayInfoId);
}
