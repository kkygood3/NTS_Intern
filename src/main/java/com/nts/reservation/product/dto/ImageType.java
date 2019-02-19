/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dto;

public enum ImageType {
	TH("TH"),
	MA("MA"),
	ET("ET");

	private final String imageWord;

	private ImageType(String imageWord) {
		this.imageWord = imageWord;
	}

	public String getImageType() {
		return imageWord;
	}

}
