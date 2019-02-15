/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dto;

import java.util.Arrays;

public enum ImageType {
	th("th"),
	ma("ma"),
	et("et");
	
	private final String imageType;
	
	private ImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public String getImageType() {
		return imageType;
	}
	
	/**
	 * Image type이 Enum에 존재하는 지 확인
	 * @param type image type
	 */
	public static boolean isExistImageType(String type) {
		return Arrays.asList(ImageType.values()).stream()
			.anyMatch(todoStatus -> todoStatus.getImageType().equals(type));
	}
}
