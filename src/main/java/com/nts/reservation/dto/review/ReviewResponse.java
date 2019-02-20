/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.review;

import java.util.List;

import com.nts.reservation.dto.common.Comment;

/**
 * detail 페이지 로드 시 /api/products/{displayInfoId} 요청에  detail 페이지의 정보를 담는 DTO
 */
public class ReviewResponse {
	private ReviewDisplayInfo reviewDisplayInfo;
	private List<Comment> reviewComment;

	public ReviewDisplayInfo getReviewDisplayInfo() {
		return reviewDisplayInfo;
	}

	public void setReviewDisplayInfo(ReviewDisplayInfo reviewDisplayInfo) {
		this.reviewDisplayInfo = reviewDisplayInfo;
	}

	public List<Comment> getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(List<Comment> reviewComment) {
		this.reviewComment = reviewComment;
	}
}
