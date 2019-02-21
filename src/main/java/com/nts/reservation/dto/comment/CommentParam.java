package com.nts.reservation.dto.comment;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
public class CommentParam implements Serializable {
	private Integer score;
	private String comment;
	private Long productId;
	private Long reservationInfoId;
	private List<MultipartFile> imageFiles;

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public List<MultipartFile> getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(List<MultipartFile> imageFiles) {
		this.imageFiles = imageFiles;
	}

	@Override
	public String toString() {
		return "CommentParam [score=" + score + ", comment=" + comment + ", productId=" + productId
			+ ", reservationInfoId=" + reservationInfoId + ", imageFiles=" + imageFiles + "]";
	}

}
