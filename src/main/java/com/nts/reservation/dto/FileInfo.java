package com.nts.reservation.dto;

import java.util.Date;

public class FileInfo {
	private long id;
	private String fileName;
	private String savaFileName;
	private String contentType;
	private boolean deleteFlag;
	private Date createDate;
	private Date modifyDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavaFileName() {
		return savaFileName;
	}

	public void setSavaFileName(String savaFileName) {
		this.savaFileName = savaFileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", fileName=" + fileName + ", savaFileName=" + savaFileName + ", contentType="
			+ contentType + ", deleteFlag=" + deleteFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate
			+ "]";
	}

}
