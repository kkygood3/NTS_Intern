/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DetailCommentResponse {
	private List<DetailComment> detailCommentList;
	private Double averageScore;
	private Integer commentCount;

	public List<DetailComment> getDetailCommentList() {
		return detailCommentList;
	}

	public void setDetailCommentList(List<DetailComment> detailCommentList) {
		this.detailCommentList = detailCommentList;
	}

	public Double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(Double averageScore) {
		this.averageScore = averageScore;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	@Override
	public String toString() {
		return "DetailCommentResponse [detailCommentList=" + detailCommentList + ", averageScore=" + averageScore
			+ ", commentCount=" + commentCount + "]";
	}
}
