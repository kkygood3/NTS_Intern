/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.CommentParam;

public interface CommentDao {
	List<Comment> selectComments(int displayInfoId, int limit);

	List<CommentImage> selectCommentImage(int commentId);

	double selectCommentAvgScore(int displayInfoId);

	int insertComment(CommentParam commentParam);

	int insertFileInfo(CommentParam commentParam, String saveFileName);

	int insertCommentImage(int reservationInfoId, int commentId, int fileId);

}
