/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.service.comment;

import java.util.List;

import com.nts.dto.comment.Comment;
import com.nts.dto.comment.Comments;

/**
 * @author 전연빈
 */
public interface CommentService {
	List<Comment> getCommentsByProductId(int productId);

	double getAverageScore(int productId);

	Comments getComments(int productId);
}
