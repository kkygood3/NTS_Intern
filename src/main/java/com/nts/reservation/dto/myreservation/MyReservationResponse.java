package com.nts.reservation.dto.myreservation;

import java.util.List;

public class MyReservationResponse {
	List<MyReservationInfo> confirmList;
	List<MyReservationInfo> completeList;
	List<MyReservationInfo> cancelList;

	public MyReservationResponse(List<MyReservationInfo> confirmList, List<MyReservationInfo> completeList,	List<MyReservationInfo> cancelList) {
		this.confirmList = confirmList;
		this.completeList = completeList;
		this.cancelList = cancelList;
	}

	public List<MyReservationInfo> getConfirmList() {
		return confirmList;
	}

	public void setConfirmList(List<MyReservationInfo> confirmList) {
		this.confirmList = confirmList;
	}

	public List<MyReservationInfo> getCompleteList() {
		return completeList;
	}

	public void setCompleteList(List<MyReservationInfo> completeList) {
		this.completeList = completeList;
	}

	public List<MyReservationInfo> getCancelList() {
		return cancelList;
	}

	public void setCancelList(List<MyReservationInfo> cancelList) {
		this.cancelList = cancelList;
	}
}
