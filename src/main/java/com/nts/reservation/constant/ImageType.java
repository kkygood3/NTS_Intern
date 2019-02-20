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
	ETC("et");

	private ImageType(String name) {
		this.name = name;
	}

	private final String name;

	public String getName() {
		return name;
	}

	public static ImageType getEnum(String name) {
		for (ImageType v : values()) {
			if (v.getName().equalsIgnoreCase(name)) {
				return v;
			}
		}
		throw new IllegalArgumentException();
	}
}
