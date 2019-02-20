/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto;

/**
* @author  : 이승수
*/
public class ReservedItem {
	private Reservation reservation;
	private DisplayInfo displayInfo;

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public DisplayInfo getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfo displayInfo) {
		this.displayInfo = displayInfo;
	}
}
