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
import com.nts.reservation.comment.service.CommentService;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDaoImpl commentDaoImpl;

	@Override
	public List<Comment> getCommentByProductId(int productId) {
		return commentDaoImpl.selectCommentByProductId(productId);
	}

	@Override
	public double getAverageScoreByProductId(int productId) {
		return commentDaoImpl.selectAverageScoreByProductId(productId);
	}
}
