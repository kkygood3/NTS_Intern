/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductPrice {
	private String createDate;
	private double discountRate;
	private String modifyDate;
	private Integer price;
	private String priceTypeName;
	private Integer productId;
	private Integer productPriceId;
}
