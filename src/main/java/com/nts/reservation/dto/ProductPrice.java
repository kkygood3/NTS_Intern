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
public class ProductPrice {
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	private int discountRate;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
	private int price;
	private String priceTypeName;
	private int productId;
	private int productPriceId;
}
