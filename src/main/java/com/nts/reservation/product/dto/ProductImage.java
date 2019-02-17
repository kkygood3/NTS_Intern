/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ProductImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer fileInfoId;
	private String fileName;
	private String modifyDate;
	private Integer productId;
	private Integer productImageId;
	private String saveFileName;
	private ImageType type;
}
