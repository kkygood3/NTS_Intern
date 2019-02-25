package com.nts.reservation.dto.reviewwrite;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.common.ReservationValidatior;
import com.nts.reservation.property.CommonProperties;

public class ReviewWriteRequest {
	private Integer id;
	private String comment;
	private Integer score;
	private Integer productId;
	private MultipartFile imageFile;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "ReviewWriteRequest [id=" + id + ", comment=" + comment + ", score=" + score + ", productId=" + productId
			+ ", imageFile=" + imageFile + "]";
	}

	public boolean isVaild() {
		return ReservationValidatior.validateComment(this.comment)
			&& ReservationValidatior.validateScore(this.score);
	}
	
	
}
