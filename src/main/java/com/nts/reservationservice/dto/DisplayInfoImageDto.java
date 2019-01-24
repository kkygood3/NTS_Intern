/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dto;

public class DisplayInfoImageDto {
	long id;
	long displayInfoId;
	long fileId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(long displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public long getFileId() {
		return fileId;
	}

	public void setFileId(long fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "DisplayInfoImageDto [id=" + id + ", displayInfoId=" + displayInfoId + ", fileId=" + fileId + "]";
	}

}
