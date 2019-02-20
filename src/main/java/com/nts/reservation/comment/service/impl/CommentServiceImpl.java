/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.DetailComment;
import com.nts.reservation.comment.dto.DetailCommentResponse;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.commons.validator.ArgumentValidator;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao commentDaoImpl;

	@Override
	public List<Comment> getAllComment(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		List<Comment> commentList = commentDaoImpl.selectAllComment(displayInfoId);

		return getImagedCommentList(commentList);
	}

	@Override
	public List<Comment> getLimitComment(int displayInfoId, int start, int limit) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);
		ArgumentValidator.checkStartAndLimit(start, limit);

		List<Comment> commentList = commentDaoImpl.selectLimitComment(displayInfoId, start, limit);

		return getImagedCommentList(commentList);
	}

	@Override
	public List<CommentImage> getCommentImage(int commentId) {
		return commentDaoImpl.selectCommentImage(commentId);
	}

	@Override
	public List<DetailComment> getDetailComment(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		return commentDaoImpl.selectDetailComment(displayInfoId);
	}

	@Override
	public DetailCommentResponse getDetailCommentResponse(int displayInfoId) {
		ArgumentValidator.checkDisplayInfoId(displayInfoId);

		DetailCommentResponse detailCommentResponse = new DetailCommentResponse();

		List<DetailComment> commentList = commentDaoImpl.selectDetailComment(displayInfoId);
		detailCommentResponse.setDetailCommentList(commentList);

		int commentListSize = commentList.size();
		detailCommentResponse.setCommentCount(commentListSize);

		double sumScore = 0.0;
		for (DetailComment comment : commentList) {
			sumScore = sumScore + comment.getScore();
		}
		detailCommentResponse.setAverageScore(sumScore / commentListSize);

		return detailCommentResponse;
	}

	private List<Comment> getImagedCommentList(List<Comment> commentList) {
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = getCommentImage(comment.getCommentId());
			if (commentImageList.size() != 0) {
				comment.setCommentImage(commentImageList.get(0).getSaveFileName());
			}
		}

		return commentList;
	}
}
