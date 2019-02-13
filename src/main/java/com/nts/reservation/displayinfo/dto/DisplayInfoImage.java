/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.displayinfo.dto;

public class DisplayInfoImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer displayInfoId;
	private Integer displayInfoImageId;
	private Integer fileId;
	private String fileName;
	private String modifyDate;
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

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public Integer getDisplayInfoImageId() {
		return displayInfoImageId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getModifyDate() {
		return modifyDate;
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

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public void setDisplayInfoImageId(Integer displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	@Override
	public String toString() {
		return "DisplayInfoImage [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag="
			+ deleteFlag + ", displayInfoId=" + displayInfoId + ", displayInfoImageId=" + displayInfoImageId
			+ ", fileId=" + fileId + ", fileName=" + fileName + ", modifyDate=" + modifyDate + ", saveFileName="
			+ saveFileName + "]";
	}
}
