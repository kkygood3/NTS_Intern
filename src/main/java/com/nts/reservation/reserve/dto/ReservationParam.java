package com.nts.reservation.reserve.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class ReservationParam {

	private int displayInfoId;
	private List<ReservationPrice> price;
	private int productId;
	private String reservationEmail;
	private String reservationName;
	private String reservationTel;
	private String reservationDate;
}
