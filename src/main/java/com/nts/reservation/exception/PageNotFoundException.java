package com.nts.reservation.exception;

public class PageNotFoundException extends Exception {
	
	public PageNotFoundException(String url) {
		super("존재하지 않는 페이지에 대한 요청입니다. [url] : " + url);
	}
}
