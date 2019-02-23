package com.nts.reservation.dto;

public class ReservationSummary {
	private String status;
	private int count;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "ReservationSummary [status=" + status + ", count=" + count + "]";
	}
}
