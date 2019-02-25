/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer fileId;
	private String fileName;
	private Integer imageId;
	private String modifyDate;
	private Integer reservationInfoId;
	private Integer reservationUserCommentId;
	private String saveFileName;
}
