package com.nts.reservation.dto;

/**
 * 에러정보를 담는 클래스
 * @author si yoon
 *
 */
public class ErrorInfo {
	private String message;
	private String statusName;
	private int status;

	public ErrorInfo(int status, String statusName, String message) {
		this.status = status;
		this.statusName = statusName;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getStatusName() {
		return statusName;
	}

	public int getStatus() {
		return status;
	}
}
