package com.nts.reservation.dto.download;

import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.common.ReservationValidatior;

public class DownloadInfo {
	private String fileName;
	private String saveFileName;
	private String contentType;

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
