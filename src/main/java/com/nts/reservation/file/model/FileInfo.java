/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.file.model;

import org.springframework.web.multipart.MultipartFile;

public class FileInfo {

	private String fileName;
	private String saveFileName;
	private String contentType;

	public FileInfo() {}

	public FileInfo(MultipartFile multipartFile, String directory, String saveFileName) {
		this.fileName = multipartFile.getOriginalFilename();
		this.saveFileName = directory + saveFileName;
		this.contentType = multipartFile.getContentType();
	}

	@Override
	public String toString() {
		return "FileInfo [fileName=" + fileName + ", saveFileName=" + saveFileName + ", contentType=" + contentType
			+ "]";
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

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
