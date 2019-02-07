/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.model.Comment;
import com.nts.reservation.model.CommentMetaData;
import com.nts.reservation.model.CommentsInfo;

public interface CommentService {

	static final boolean LIMITED = true;
	static final boolean NOT_LIMITED = false;

	CommentsInfo getCommentsInfo(int displayInfoId, boolean limit);

	List<Comment> getCommentList(int displayInfoId, boolean limit);

	CommentMetaData getCommentMetaData(int displayInfoId);
}
