/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dto;

/**
 * @author 육성렬
 */
public class FileDto {
	private String contentType;
	private String fileName;
	private String saveFileName;

	public FileDto(String contentType, String fileName, String saveFileName) {
		super();
		this.contentType = contentType;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
	}

	public FileDto() {};

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
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

	@Override
	public String toString() {
		return "FileDto [contentType=" + contentType + ", fileName=" + fileName + ", saveFileName=" + saveFileName
			+ "]";
	}

}
