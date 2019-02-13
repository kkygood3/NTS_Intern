/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.service;

import java.util.List;

import com.nts.dto.commentdto.Comment;
import com.nts.dto.commentdto.CommentImage;

/**
 * 
 *
 * @description :  
 * @package : com.nts.service
 * @filename : CommentService.java
 * @author : 최석현
 * @method :double getAverageScoreByDisplayInfoId(int displayInfoId)
 * @method :List<Comment> getCommentsByDisplayInfoId(int displayInfoId)
 * @method :CommentImage getCommentImageByReservationUserCommentId(int reservationUserCommentId)
 */
public interface CommentService {

	public double getAverageScoreByDisplayInfoId(int displayInfoId);

	public List<Comment> getCommentsByDisplayInfoId(int displayInfoId);
	
	public CommentImage getCommentImageByReservationUserCommentId(int reservationUserCommentId);
}
