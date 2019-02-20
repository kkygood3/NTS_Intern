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

	public String getContentType() {
		return contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	@Override
	public String toString() {
		return "FileDto [contentType=" + contentType + ", fileName=" + fileName + ", saveFileName=" + saveFileName
			+ "]";
	}

}
