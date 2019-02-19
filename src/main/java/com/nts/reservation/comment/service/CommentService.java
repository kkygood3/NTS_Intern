/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service;

import java.util.List;

import com.nts.reservation.comment.model.Comment;
import com.nts.reservation.comment.model.CommentListInfo;

public interface CommentService {

	static final int COUNT_LIMITED = 3;
	static final int COUNT_NOT_LIMITED = 0;

	CommentListInfo getCommentListInfo(int displayInfoId, int limitCount);

	List<Comment> getCommentList(int displayInfoId, int limitCount);
}
