/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.MyReservationDto;
import com.nts.reservation.util.DateUtil;

/**
 * 나의 예약정보 response용 DTO
 * @author jinwoo.bae
 */
public class MyReservationResponseDto {
	private List<MyReservationDto> myReservations;
	private int totalCount; // 전체 개수
	private int todoCount; // 이용 예정 개수
	private int doneCount; // 이용 완료 개수
	private int cancleCount; // 취소 개수

	public MyReservationResponseDto(List<MyReservationDto> myReservations) {
		setMyReservations(myReservations);
		setTotalCount(myReservations.size());

		int todoCount = 0;
		int doneCount = 0;
		int cancleCount = 0;

		for (MyReservationDto myReservation : myReservations) {
			if (myReservation.getCancelFlag()) {
				cancleCount += 1;
			} else if (DateUtil.isAfterToday(myReservation.getReservationDate())) {
				doneCount += 1;
			} else {
				todoCount += 1;
			}
		}
		setTodoCount(todoCount);
		setDoneCount(doneCount);
		setCancleCount(cancleCount);
	}

	public List<MyReservationDto> getMyReservations() {
		return myReservations;
	}

	public void setMyReservations(List<MyReservationDto> myReservations) {
		this.myReservations = myReservations;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTodoCount() {
		return todoCount;
	}

	public void setTodoCount(int todoCount) {
		this.todoCount = todoCount;
	}

	public int getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}

	public int getCancleCount() {
		return cancleCount;
	}

	public void setCancleCount(int cancleCount) {
		this.cancleCount = cancleCount;
	}

	@Override
	public String toString() {
		return "MyReservationResponseDto [myReservations=" + myReservations + ", totalCount=" + totalCount
			+ ", todoCount=" + todoCount + ", doneCount=" + doneCount + ", cancleCount=" + cancleCount + "]";
	}

}
