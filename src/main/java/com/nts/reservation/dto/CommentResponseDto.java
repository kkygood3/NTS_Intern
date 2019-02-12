/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.List;

/**
 * 상품평 정보들과 해당 프로덕트의 상품평 개수를 담는 response용 DTO 클래스
 * @author jinwoo.bae
 */
public class CommentResponseDto {
	private List<CommentDto> comments;
	private int totalCount;
	private double averageScore;

	public CommentResponseDto(List<CommentDto> comments, int count, double averageScore) {
		this.comments = comments;
		this.totalCount = count;
		this.averageScore = averageScore;
	}

	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

}
