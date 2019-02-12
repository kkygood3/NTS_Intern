/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private String createDate;
	private String modifyDate;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private double score;
}
