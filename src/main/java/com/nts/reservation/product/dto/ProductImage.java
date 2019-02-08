/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dto;

import lombok.Data;

@Data
public class ProductImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private int fileInfoId;
	private String fileName;
	private String modifyDate;
	private int productId;
	private int productImageId;
	private String saveFileName;
	private String type;
	
	// TODO Enum
}
