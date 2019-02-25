package com.nts.reservation.property;

public enum ReservationStatus {
	CONFIRMED("confirmed"), USED("used"), CANCEL("cancel");

	private String status;

	private ReservationStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
