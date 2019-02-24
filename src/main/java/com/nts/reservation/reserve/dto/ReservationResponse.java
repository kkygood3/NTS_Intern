package com.nts.reservation.reserve.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class ReservationResponse {

	private ReservationInfo reservationInfo;
	private List<ReservationPrice> price;
}
