package com.nts.reservation.dto.detail;
/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 */

import java.time.LocalDateTime;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 */

public class CommentImage {
	private Long imageId;
	private Long reservationInfoId;
	private Long fileId;
	private String fileName;
	private String saveFileName;
	private String contentType;
	private Boolean deleteFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
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

	public Boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "CommentImage [imageId=" + imageId + ", reservationInfoId=" + reservationInfoId + ", fileId=" + fileId
			+ ", fileName=" + fileName + ", saveFileName=" + saveFileName + ", contentType=" + contentType
			+ ", deleteFlag=" + deleteFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}

}
