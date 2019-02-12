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
public class ProductImage {
	private String contentType;
	private LocalDateTime createDate;
	private boolean deleteFlag;
	private int fileInfoId;
	private String fileName;
	private LocalDateTime modifyDate;
	private int productId;
	private int productImageId;
	private String saveFileName;
	private String type;
	
	// TODO Enum
}
