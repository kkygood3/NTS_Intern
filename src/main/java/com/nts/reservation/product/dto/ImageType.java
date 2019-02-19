/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dto;

import lombok.Getter;

@Getter
public enum ImageType {
	TH("th"),
	MA("ma"),
	ET("et");

	private final String imageCode;

	private ImageType(String imageCode) {
		this.imageCode = imageCode;
	}
}
