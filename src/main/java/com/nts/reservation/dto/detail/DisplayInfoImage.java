package com.nts.reservation.dto.detail;

/**
 * DetailResponse의 displayInfoImage 멤버 변수로 사용
 * @see	DetailResponse
 */
public class DisplayInfoImage {
	private String contentType;
	private String createDate;
	private int deleteFlag;
	private int displayInfoId;
	private int displayInfoImageId;
	private int fileId;
	private String fileName;
	private String modifyDate;
	private String saveFileName;

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

	public int getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public int getDisplayInfoImageId() {
		return displayInfoImageId;
	}

	public void setDisplayInfoImageId(int displayInfoImageId) {
		this.displayInfoImageId = displayInfoImageId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
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

	public String getSaveFileName() {
		return saveFileName;
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
