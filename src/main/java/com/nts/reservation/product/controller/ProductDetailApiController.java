/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;
import com.nts.reservation.comment.dto.CommentResponse;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
@RestController
public class ProductDetailApiController {
	@Autowired
	private CommentService commentService;

	@GetMapping("/api/products/{displayInfoId}")
	public CommentResponse getItems(@PathVariable int displayInfoId,
		@RequestParam(name = "start", required = false, defaultValue = "0") int start,
		@RequestParam(name = "limit", required = false, defaultValue = "3") int limit) {

		if (isInvalidDisplayInfoId(displayInfoId)) {
			System.out.println("올바르지 않은 displayInfoId");

			return getEmptyCommentResponse();
		}
		if (isInvalidStartAndLimit(start, limit)) {
			System.out.println("올바르지 않은 start, limit");

			return getEmptyCommentResponse();
		}

		// 아니면 평균 점수 계산을 쿼리문 없이 프론트에서?
		double averageScore = commentService.getAverageScoreByDisplayInfoId(displayInfoId);

		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		return getLimitCommentResponse(displayInfoId, start, limit);
		//return getAllCommentResponse(displayInfoId);
	}

	private boolean isValidDisplayInfoId(int displayInfoId) {
		if (displayInfoId <= 0) {
			return false;
		}
		return true;
	}

	private boolean isInvalidDisplayInfoId(int displayInfoId) {
		return !isValidDisplayInfoId(displayInfoId);
	}

	private boolean isValidStartAndLimit(int start, int limit) {
		if (start < 0 || limit < 0) {
			return false;
		}
		return true;
	}

	private boolean isInvalidStartAndLimit(int start, int limit) {
		return !isValidStartAndLimit(start, limit);
	}

	private CommentResponse getEmptyCommentResponse() {
		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setItems(Collections.emptyList());

		return commentResponse;
	}

	private CommentResponse getAllCommentResponse(int displayInfoId) {
		List<Comment> commentList = commentService.getAllCommentByDisplayInfoId(displayInfoId);
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = commentService.getCommentImageByCommentId(comment.getCommentId());
			comment.setCommentImages(commentImageList);
		}

		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setItems(commentList);

		return commentResponse;
	}

	private CommentResponse getLimitCommentResponse(int displayInfoId, int start, int limit) {
		List<Comment> commentList = commentService.getLimitCommentByDisplayInfoId(displayInfoId, start, limit);
		List<CommentImage> commentImageList = new ArrayList<CommentImage>();

		for (Comment comment : commentList) {
			commentImageList = commentService.getCommentImageByCommentId(comment.getCommentId());
			comment.setCommentImages(commentImageList);
		}

		CommentResponse commentResponse = new CommentResponse();
		commentResponse.setItems(commentList);

		return commentResponse;
	}
}
