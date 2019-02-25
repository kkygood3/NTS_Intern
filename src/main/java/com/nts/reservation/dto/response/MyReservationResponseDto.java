/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

/**
 * 나의 예약정보 response용 DTO
 * @author jinwoo.bae
 */
public class MyReservationResponseDto {
	private ReservationResponseDto todoReservationResponse; // 이용예정 예약 response
	private ReservationResponseDto doneReservationResponse; // 이용완료 예약 response
	private ReservationResponseDto cancelReservationResponse; // 취소된 예약 response
	private int totalCount; // 전체 개수

	public MyReservationResponseDto(ReservationResponseDto todoReservationResponse,
		ReservationResponseDto doneReservationResponse, ReservationResponseDto cancelReservationResponse) {
		setTodoReservationResponse(todoReservationResponse);
		setDoneReservationResponse(doneReservationResponse);
		setCancelReservationResponse(cancelReservationResponse);
		setTotalCount();
	}

	public ReservationResponseDto getTodoReservationResponse() {
		return todoReservationResponse;
	}

	public void setTodoReservationResponse(ReservationResponseDto todoReservationResponse) {
		this.todoReservationResponse = todoReservationResponse;
	}

	public ReservationResponseDto getDoneReservationResponse() {
		return doneReservationResponse;
	}

	public void setDoneReservationResponse(ReservationResponseDto doneReservationResponse) {
		this.doneReservationResponse = doneReservationResponse;
	}

	public ReservationResponseDto getCancelReservationResponse() {
		return cancelReservationResponse;
	}

	public void setCancelReservationResponse(ReservationResponseDto cancleReservationResponse) {
		this.cancelReservationResponse = cancleReservationResponse;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount() {
		this.totalCount = todoReservationResponse.getTotalCount() + doneReservationResponse.getTotalCount()
			+ cancelReservationResponse.getTotalCount();
	}

}
