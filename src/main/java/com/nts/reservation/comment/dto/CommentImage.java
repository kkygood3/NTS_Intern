/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.comment.dto;

public class CommentImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer fileId;
	private String fileName;
	private Integer imageId;
	private String modifyDate;
	private Integer reservationInfoId;
	private Integer reservationUserCommentId;
	private String saveFileName;

	public String getContentType() {
		return contentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public Integer getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public Integer getImageId() {
		return imageId;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public Integer getReservationInfoId() {
		return reservationInfoId;
	}

	public Integer getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public void setReservationUserCommentId(Integer reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "CommentImage [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag=" + deleteFlag
			+ ", fileId=" + fileId + ", fileName=" + fileName + ", imageId=" + imageId + ", modifyDate=" + modifyDate
			+ ", reservationInfoId=" + reservationInfoId + ", reservationUserCommentId=" + reservationUserCommentId
			+ ", saveFileName=" + saveFileName + "]";
	}
}
