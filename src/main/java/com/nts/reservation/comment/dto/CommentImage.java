/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class CommentImage {
	private String contentType;
	private LocalDateTime createDate;
	private boolean deleteFlag;
	private int fileId;
	private String fileName;
	private int imageId;
	private LocalDateTime modifyDate;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private String saveFileName;
	
}
