package com.nts.reservation.reserve.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class ReservationResponse {

	private boolean cancelYn;
	private String createDate;
	private int displayInfoId;
	private String modifyDate;
	private List<ReservationPrice> price;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTel;
}
