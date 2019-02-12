/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductPrice {
	private LocalDateTime createDate;
	private double discountRate;
	private LocalDateTime modifyDate;
	private int price;
	private String priceTypeName;
	private int productId;
	private int productPriceId;
}
