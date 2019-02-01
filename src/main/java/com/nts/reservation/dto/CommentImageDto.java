/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

import java.util.Date;

/**
 * @author 육성렬
 */
public class CommentImageDto {
	private String contentType;
	private Date createDate;
	private boolean deleteFlag;
	private Long fileId;
	private String fileName;
	private Long imageId;
	private Date modifyDate;
	private Long reservationInfoId;
	private Long reservationUserCommentId;
	private String saveFileName;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getReservationInfoId() {
		return reservationInfoId;
	}

	public void setReservationInfoId(Long reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}

	public Long getReservationUserCommentId() {
		return reservationUserCommentId;
	}

	public void setReservationUserCommentId(Long reservationUserCommentId) {
		this.reservationUserCommentId = reservationUserCommentId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "CommentImageDto [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag="
			+ deleteFlag + ", fileId=" + fileId + ", fileName=" + fileName + ", imageId=" + imageId + ", modifyDate="
			+ modifyDate + ", reservationInfoId=" + reservationInfoId + ", reservationUserCommentId="
			+ reservationUserCommentId + ", saveFileName=" + saveFileName + "]";
	}
}
