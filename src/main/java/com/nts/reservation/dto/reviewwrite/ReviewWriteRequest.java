package com.nts.reservation.dto.reviewwrite;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.common.ReservationValidatior;

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

	public Integer getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(Integer reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Integer getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(Integer fileInfoId) {
		this.fileInfoId = fileInfoId;
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

	public boolean isVaild() {
		boolean result = ReservationValidatior.validateComment(this.comment)
			&& ReservationValidatior.validateScore(this.score);
		if (imageFile != null) {
			result = result && ReservationValidatior.validateImage(this.imageFile);
		}
		return result;
	}
}
