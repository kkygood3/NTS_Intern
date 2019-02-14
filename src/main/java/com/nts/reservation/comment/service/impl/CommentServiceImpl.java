/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.impl.CommentDaoImpl;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.service.CommentService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDaoImpl commentDaoImpl;

	@Override
	public List<Comment> getAllCommentByDisplayInfoId(int displayInfoId) {
		return commentDaoImpl.selectAllCommentByDisplayInfoId(displayInfoId);
	}

	@Override
	public List<Comment> getLimitCommentByDisplayInfoId(int displayInfoId, int start, int limit) {
		return commentDaoImpl.selectLimitCommentByDisplayInfoId(displayInfoId, start, limit);
	}

	@Override
	public List<CommentImage> getCommentImageByCommentId(int commentId) {
		return commentDaoImpl.selectCommentImageByCommentId(commentId);
	}

	@Override
	public double getAverageScoreByDisplayInfoId(int displayInfoId) {
		return commentDaoImpl.selectAverageScoreByDisplayInfoId(displayInfoId);
	}
}
