/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dto;

public class ProductImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer fileInfoId;
	private String fileName;
	private String modifyDate;
	private Integer productId;
	private Integer productImageId;
	private String saveFileName;
	private String type;	// TODO ENUM 적용

	public String getContentType() {
		return contentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public Integer getFileInfoId() {
		return fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public Integer getProductId() {
		return productId;
	}

	public Integer getProductImageId() {
		return productImageId;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public String getType() {
		return type;
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

	public void setFileInfoId(Integer fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setProductImageId(Integer productImageId) {
		this.productImageId = productImageId;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ProductImage [contentType=" + contentType + ", createDate=" + createDate + ", deleteFlag=" + deleteFlag
			+ ", fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", modifyDate=" + modifyDate + ", productId="
			+ productId + ", productImageId=" + productImageId + ", saveFileName=" + saveFileName + ", type=" + type
			+ "]";
	}

}
