/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
* @author  : 이승수
*/
@Data
public class Reservation {
	private boolean cancelYn;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date createDate;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date modifyDate;
	private int id;
	private int displayInfoId;
	private List<ReservationPrice> prices;
	private int productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTel;
	@JsonFormat(pattern = "yyyy.MM.dd")
	private Date reservationDate;
	private int totalPrice;
}