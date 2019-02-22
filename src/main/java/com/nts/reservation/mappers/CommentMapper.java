package com.nts.reservation.mappers;

import java.util.List;

import com.nts.reservation.dto.comment.Comment;
import com.nts.reservation.dto.comment.CommentImage;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public interface CommentMapper {
	public List<Comment> getCommentsWithImages(Long displayInfoId);

	public List<CommentImage> getCommentImagesByCommentId(Long commentId);
}
