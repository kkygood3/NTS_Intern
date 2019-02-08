package com.nts.reservation.dto.detail;

public class ProductImage {
	private String contentType;
	private String createDate;
	private int deleteFlag;
	private int fileInfoId;
	private String fileName;
	private String modifyDate;
	private String productId;
	private String productImageId;
	private String saveFileName;
	private String Type;//DTO 테스트 후에 ENUM으로 변경할 수 있는지 확인예정
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public int getFileInfoId() {
		return fileInfoId;
	}
	public void setFileInfoId(int fileInfoId) {
		this.fileInfoId = fileInfoId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductImageId() {
		return productImageId;
	}
	public void setProductImageId(String productImageId) {
		this.productImageId = productImageId;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
}
