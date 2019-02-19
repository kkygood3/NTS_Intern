package com.nts.reservation.reserve.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Builder
public class ReservationInfoResponse {

	private List<ReservationInfo> reservations;
	private int size;
}
