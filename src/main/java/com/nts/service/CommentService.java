/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

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

	double getAverageScoreByDisplayInfoId(int displayInfoId);

	List<Comment> getCommentsByDisplayInfoId(int displayInfoId);
	
	CommentImage getCommentImageByReservationUserCommentId(int reservationUserCommentId);
	
	void addComment(Comment comment, MultipartFile reservationImage, String imagePath) throws FileNotFoundException, IOException;
	
	void addCommentImage(CommentImage commentImage, MultipartFile reservationImage);
}
