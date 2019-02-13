/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.service;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;

public interface CommentService {
	List<Comment> getComments(int displayInfoId, int count);
	List<Comment> getComments(int displayInfoId);
}
