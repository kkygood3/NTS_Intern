/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Product {
	private Integer displayInfoId;
	private String placeName;
	private String productContent;
	private String productDescription;
	private Integer productId;
	private String productImageUrl;
}
