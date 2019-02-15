/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

import java.util.Collections;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class CommentResponse {
	private List<Comment> comments;
	private double avgScore;
	
	public static CommentResponse emptyCommentResponse() {
		return CommentResponse.builder()
			.comments(Collections.emptyList()).build();
	}
}
