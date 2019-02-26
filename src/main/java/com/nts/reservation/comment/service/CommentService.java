/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.service;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImageDownload;

public interface CommentService {
	List<Comment> getComments(int displayInfoId, int count);
	List<Comment> getTotalComments(int displayInfoId);
	CommentImageDownload getCommentImage(int reservationInfoImageId);
}
