/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public CommentResponse getComments(int displayInfoId, int limit) {
		List<Comment> comments = commentDao.selectCommentBydisplayInfoId(displayInfoId, limit);

		if (comments.size() == 0) {
			return CommentResponse.emptyCommentResponse();
		}

		for (Comment comment : comments) {
			comment.setReservationEmail(makeBlindEmail(comment.getReservationEmail()));
			comment.setReservationDate(formattingDate(comment.getReservationDate()));
			if (comment.getImageCount() < 0) {
				continue;
			}
			List<CommentImage> commentImages = commentDao.selectCommentImage(comment.getCommentId());
			comment.setCommentImages(commentImages);
		}

		double avgScore = commentDao.selectCommentAvgScore(displayInfoId);
		return CommentResponse.builder()
			.comments(comments)
			.avgScore(avgScore).build();
	}

	private String makeBlindEmail(String email) {
		String splicedEmail = email.split("@")[0];
		int blindPoint = splicedEmail.length() / 2;
		String blindEmail = splicedEmail.substring(0, blindPoint)
			+ splicedEmail.substring(blindPoint).replaceAll("[a-zA-Z0-9]", "*");
		return blindEmail;
	}
	
	private String formattingDate(String date) {
		return date.split(" ")[0].replaceAll("-", ". ") + ". ";
	}
}
