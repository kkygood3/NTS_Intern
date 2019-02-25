/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.constant;

/**
 * 이미지 타입
 * @author jinwoo.bae
 */
public enum ImageType {
	THUMBNAIL("th"),
	MAIN("ma"),
	ET("et");

	private ImageType(String code) {
		this.code = code;
	}

	private final String code;

	public String getCode() {
		return code;
	}

	public static ImageType getEnum(String name) {
		for (ImageType v : values()) {
			if (v.getCode().equalsIgnoreCase(name)) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}

}
