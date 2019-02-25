package com.nts.reservation.reserve.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
public class ReservationPrice {
	private int reservationInfoPriceId;
	private int reservationInfoId;
	private int productPriceId;
	private int count;
	
}
