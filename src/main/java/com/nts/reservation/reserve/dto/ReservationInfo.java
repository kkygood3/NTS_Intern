package com.nts.reservation.reserve.dto;

import com.nts.reservation.display.dto.DisplayInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@NoArgsConstructor
public class ReservationInfo {

	private boolean cancelYn;
	private String createDate;
	private DisplayInfo displayInfo;
	private int displayInfoId;
	private String modifyDate;
	private int productId;
	private String reservationDate;
	private String reservationEmail;
	private int reservationInfoId;
	private String reservationName;
	private String reservationTel;
	private int totalPrice;
}
