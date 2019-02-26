/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReviewWriteRequest {
	private Integer reservationUserCommentId;
	private String comment;
	private Integer score;
	private Integer productId;
	private MultipartFile imageFile;
	private Integer reservationInfoId;
	private Integer fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contentType;
}