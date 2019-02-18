/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.impl;

import java.util.ArrayList;
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
	public List<Comment> getAllComment(int displayInfoId) {
		List<Comment> commentList = commentDaoImpl.selectAllComment(displayInfoId);
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = getCommentImage(comment.getCommentId());
			comment.setCommentImages(commentImageList);
		}

		return commentList;
	}

	@Override
	public List<Comment> getLimitComment(int displayInfoId, int start, int limit) {
		List<Comment> commentList = commentDaoImpl.selectLimitComment(displayInfoId, start, limit);
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = getCommentImage(comment.getCommentId());
			comment.setCommentImages(commentImageList);
		}

		return commentList;
	}

	@Override
	public List<CommentImage> getCommentImage(int commentId) {
		return commentDaoImpl.selectCommentImage(commentId);
	}

	@Override
	public double getAverageScore(int displayInfoId) {
		return commentDaoImpl.selectAverageScore(displayInfoId);
	}
}
