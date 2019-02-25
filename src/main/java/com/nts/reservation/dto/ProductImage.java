/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* @author  : 이승수
*/
@Data
public class ProductImage {
	private String contentType;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	private boolean deleteFlag;
	private int fileInfoId;
	private String fileName;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
	private int productId;
	private int productImageId;
	private String saveFileName;
	private String type;
}
