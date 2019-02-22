package com.nts.reservation.dto.product;
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

public class ProductImage {
	private Long productId;
	private Long productImageId;
	private String type;
	private Long fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contenttype;
	private Boolean deleteFlag;
	private LocalDateTime createDate;
	private LocalDateTime modifyDate;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(Long productImageId) {
		this.productImageId = productImageId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
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

	public Long getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(Long fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ProductImage [productId=" + productId + ", productImageId=" + productImageId + ", type=" + type
			+ ", fileInfoId=" + fileInfoId + ", fileName=" + fileName + ", saveFileName=" + saveFileName
			+ ", contenttype=" + contenttype + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate
			+ ", modifyDate=" + modifyDate + "]";
	}

}
