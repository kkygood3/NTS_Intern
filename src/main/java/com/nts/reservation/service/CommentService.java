package com.nts.reservation.service;

import java.util.List;

import com.nts.reservation.dto.comment.CommentParam;
import com.nts.reservation.dto.common.CommentImage;
import com.nts.reservation.dto.detail.Comment;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public interface CommentService {

	public static final String basePath = "C:/Users/USER/eclipse-workspace/reservation/resources/";

	CommentImage getCommentImageById(Long commentImageId);

	List<Comment> getComments(Long displayInfoId);

	void postComments(CommentParam commentParam);

	Long checkExistingComment(Long reservationId);

}
