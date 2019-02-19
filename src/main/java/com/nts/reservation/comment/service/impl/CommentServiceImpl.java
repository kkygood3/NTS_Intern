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
		List<Comment> comment = commentDaoImpl.selectCommentByLimit(displayInfoId, count);

		setCommentImagesByCommentId(comment);

		return comment;
	}

	@Override
	public List<Comment> getTotalComments(int displayInfoId) {
		List<Comment> comment = commentDaoImpl.selectComment(displayInfoId);

		setCommentImagesByCommentId(comment);

		return comment;
	}

	private List<Comment> setCommentImagesByCommentId(List<Comment> comment){
		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDaoImpl.selectCommentImages(commentItem.getCommentId()));
		});

		return comment;
	}

}
