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
public class DisplayInfo {
	private int categoryId;
	private String categoryName;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	private int displayInfoId;
	private String email;
	private String homepage;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
	private String openingHours;
	private String placeLot;
	private String placeName;
	private String placeStreet;
	private String productContent;
	private String productDescription;
	private String productEvent;
	private int productId;
	private String telephone;
}
