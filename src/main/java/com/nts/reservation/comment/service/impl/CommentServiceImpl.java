/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.impl.CommentDaoImpl;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDaoImpl commentDaoImpl;

	@Override
	public List<Comment> getComments(int displayInfoId, int count) {
		List<Comment> comment = new ArrayList<Comment>();

		if(isRequiredTotalComments(count)) {
			return getComments(displayInfoId);
		}

		comment = commentDaoImpl.selectCommentByDisplayInfoIdByLimit(displayInfoId, count);
		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDaoImpl.selectCommentImagesByDisplayInfoId(displayInfoId));
		});

		return comment;
	}

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comment = new ArrayList<Comment>();

		comment = commentDaoImpl.selectCommentByDisplayInfoId(displayInfoId);
		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDaoImpl.selectCommentImagesByDisplayInfoId(displayInfoId));
		});

		return comment;
	}

	private boolean isRequiredTotalComments(int count) {
		if(count > 0) {
			return false;
		}
		return true;
	}

}
