package com.nts.reservation.exception;

public class UnauthorizedRequestException extends Exception{
	
	public UnauthorizedRequestException(String ipAddress, String requestUrl) {
		super("권한이 없는 요청입니다. [ipAddress]: " + ipAddress +" [requestUrl]: " + requestUrl);
	}
}
