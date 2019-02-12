/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.serviceImpl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comments = commentDao.selectAllCommentBydisplayInfoId(displayInfoId);

		if (comments.size() == 0) {
			return Collections.emptyList();
		}

		comments.forEach(comment -> {
			List<CommentImage> commentImages = commentDao.selectCommentImage(displayInfoId, comment.getCommentId());


			comment.setCommentImages(commentImages);
			comment.setReservationEmail(makeBlindEmail(comment.getReservationEmail()));
		});

		return comments;
	}
	
	@Override
	public double getCommentAvgScore(int displayInfoId) {
		return commentDao.selectCommentAvgScore(displayInfoId);
	}

	private String makeBlindEmail(String email) {
		String splicedEmail = email.split("@")[0];
		int blindPoint = splicedEmail.length() / 2;
		String blindEmail = splicedEmail.substring(0, blindPoint)
			+ splicedEmail.substring(blindPoint).replaceAll("[a-zA-Z0-9]", "*");
		return blindEmail;
	}
}
