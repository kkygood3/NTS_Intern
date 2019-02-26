/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* @author  : 이승수
*/
@Data
public class CommentImage {
	private String contentType;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	private boolean deleteFlag;
	private int fileId;
	private String fileName;
	private int imageId;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private String saveFileName;
}
