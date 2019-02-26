/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.param;

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

/**
 * 상품평 등록 파라미터용 Dto
 * @author jinwoo.bae
 */
public class CommentParamDto {
	private int id;
	private int productId;
	private int reservationId;
	private int score;
	private String comment;
	private MultipartFile image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CommentParamDto [productId=" + productId + ", reservationId=" + reservationId + ", score=" + score
			+ ", comment=" + comment + ", image=" + image + "]";
	}

	public boolean isValid() {
		if (score <= 0) {
			return false;
		}
		if (comment.length() < 5 || comment.length() > 400) {
			return false;
		}
		String[] AllowedImageTypes = {"image/png", "image/jpeg"};
		if (image != null && !Arrays.stream(AllowedImageTypes).anyMatch(image.getContentType()::equals)) {
			return false;
		}
		return true;
	}

}
