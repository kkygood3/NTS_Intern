package com.nts.reservation.detail.dto;

import java.time.LocalDateTime;

public class ProductImage {
	private Long productId;
	private Long productImageId;
	private String type;
	private String saveFileName;
	private String contenttype;
	private boolean deleteFlag;
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

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
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
		return "ProductImages [productId=" + productId + ", productImageId=" + productImageId + ", type=" + type
			+ ", saveFileName=" + saveFileName + ", contenttype=" + contenttype + ", deleteFlag=" + deleteFlag
			+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
}
