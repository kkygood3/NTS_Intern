/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReservationParam {
	private final String emailReg = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
	
	@NotNull @Min(0)
	private Integer displayInfoId;
	@NotNull
	private List<ReservationPrice> prices;
	@NotNull @Min(0)
	private Integer productId;
	@NotNull @Pattern(regexp=emailReg)
	private String reservationEmail;
	@NotNull
	private String reservationName;
	@NotNull
	private String reservationTelephone;
	@NotNull
	private String reservationYearMonthDay;

}
