/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentResponse {
	private String comment;
	private Integer commentId;
	private List<CommentImage> commentImage;
	private String createDate;
	private String modifyDate;
	private Integer productId;
	private Integer reservationInfoId;
	private Integer score;
}
