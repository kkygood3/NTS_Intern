/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReservationResponseData {
	private boolean cancelYn;
	private String createDate;
	private Integer displayInfoId;
	private String modifyDate;
	private Integer productId;
	private String reservationDate;
	private String reservationEmail;
	private Integer reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
}
