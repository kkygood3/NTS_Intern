package com.nts.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.dto.common.CommentImage;
import com.nts.reservation.dto.detail.Comment;
import com.nts.reservation.service.CommentService;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Service
@Transactional(readOnly = true)
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDao;

	@Override
	public CommentImage getCommentImageById(Long commentImageId) {
		return commentDao.selectCommentImagebyImageId(commentImageId);
	}

	@Override
	public List<Comment> getComments(Long displayInfoId) {
		List<Comment> comments = commentDao.selectComments(displayInfoId);
		for (Comment comment : comments) {
			List<CommentImage> list = commentDao.selectCommentsImagesByCommentId(comment.getCommentId());
			comment.setCommentImages(list);
		}
		return comments;
	}

}
