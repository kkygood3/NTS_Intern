/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import lombok.Data;

@Data
public class Promotion {
	private int id;
	private int productId;
	private String productImageUrl;
}
