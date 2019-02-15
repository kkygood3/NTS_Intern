/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDaoImpl;

	@Override
	public List<Comment> getComments(int displayInfoId, int count) {
		if(isRequiredTotalComments(count)) {
			return getComments(displayInfoId);
		}

		List<Comment> comment = commentDaoImpl.selectCommentByLimit(displayInfoId, count);
		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDaoImpl.selectCommentImages(displayInfoId));
		});

		return comment;
	}

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comment = commentDaoImpl.selectComment(displayInfoId);

		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDaoImpl.selectCommentImages(displayInfoId));
		});

		return comment;
	}

	private boolean isRequiredTotalComments(int count) {
		return (count == 0);
	}

}
