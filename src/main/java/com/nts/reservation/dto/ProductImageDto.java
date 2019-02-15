/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import com.nts.reservation.constant.ImageType;

/**
 * 상품 이미지 모델
 * @author jinwoo.bae
 */
public class ProductImageDto {
	int id;
	ImageType type; // 이미지 타입 (main, thumbnail, etc)
	String saveFileName; // 이미지 저장 경로
	Boolean deleteFlag; // 삭제 여부

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ImageType getType() {
		return type;
	}

	public void setType(String type) {
		this.type = ImageType.getEnum(type);
	}

	public String getSaveFileName() {
		return saveFileName;
	}

	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
