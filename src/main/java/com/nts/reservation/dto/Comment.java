/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* @author  : 이승수
*/
@Data
public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages = Collections.emptyList();
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
	private int productId;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private BigDecimal score;

	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail.substring(0, 4) + "****";
	}
}
