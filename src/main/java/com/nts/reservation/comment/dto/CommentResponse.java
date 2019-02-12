/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentResponse {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;
	private int productId;
	private int reservationInfoId;
	private double score;
}
