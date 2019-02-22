/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service;

import com.nts.reservation.dto.Comment;
import com.nts.reservation.dto.CommentImage;

/**
* @author  : 이승수
*/
public interface CommentService {
	public int setComment(Comment comment);

	public void setCommentImage(CommentImage commentImage);
}
