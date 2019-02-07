/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.dao.CommentDao;
import com.nts.reservation.model.Comment;
import com.nts.reservation.model.CommentMetaData;
import com.nts.reservation.model.CommentsInfo;
import com.nts.reservation.service.CommentService;

@Service
public class CommentServiceLogic implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public CommentsInfo getCommentsInfo(int displayInfoId, boolean limit) {
		CommentMetaData commentMetaData = getCommentMetaData(displayInfoId);
		List<Comment> commentList = getCommentList(displayInfoId, limit);

		return new CommentsInfo(commentMetaData, commentList);
	}

	@Override
	public List<Comment> getCommentList(int displayInfoId, boolean limit) {
		if (limit) {
			return commentDao.getLimitedCommentList(displayInfoId);
		} else {
			return commentDao.getCommentList(displayInfoId);
		}
	}

	@Override
	public CommentMetaData getCommentMetaData(int displayInfoId) {
		return commentDao.getCommentMetaData(displayInfoId);
	}
}
