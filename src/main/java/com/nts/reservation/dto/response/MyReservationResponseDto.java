/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.Collections;
import java.util.List;

import com.nts.reservation.dto.MyReservationDto;
import com.nts.reservation.util.DateUtil;

/**
 * 나의 예약정보 response용 DTO
 * @author jinwoo.bae
 */
public class MyReservationResponseDto {
	private List<MyReservationDto> todoReservations; // 이용예정 예약들
	private List<MyReservationDto> doneReservations; // 이용완료 예약들
	private List<MyReservationDto> cancleReservations; // 취소된 예약들
	private int totalCount; // 전체 개수
	private int todoCount; // 이용 예정 개수
	private int doneCount; // 이용 완료 개수
	private int cancleCount; // 취소 개수

	public MyReservationResponseDto(List<MyReservationDto> myReservations) {
		setTotalCount(myReservations.size());
		List<MyReservationDto> todoReservations = Collections.emptyList();
		List<MyReservationDto> doneReservations = Collections.emptyList();
		List<MyReservationDto> cancleReservations = Collections.emptyList();

		for (MyReservationDto myReservation : myReservations) {
			if (myReservation.getCancelFlag()) {
				cancleReservations.add(myReservation);
			} else if (DateUtil.isAfterToday(myReservation.getReservationDate())) {
				doneReservations.add(myReservation);
			} else {
				todoReservations.add(myReservation);
			}
		}
		setTodoReservations(todoReservations);
		setDoneReservations(doneReservations);
		setCancleReservations(cancleReservations);
		setTodoCount(todoReservations.size());
		setDoneCount(doneReservations.size());
		setCancleCount(cancleReservations.size());
	}

	public List<MyReservationDto> getTodoReservations() {
		return todoReservations;
	}

	public void setTodoReservations(List<MyReservationDto> todoReservations) {
		this.todoReservations = todoReservations;
	}

	public List<MyReservationDto> getDoneReservations() {
		return doneReservations;
	}

	public void setDoneReservations(List<MyReservationDto> doneReservations) {
		this.doneReservations = doneReservations;
	}

	public List<MyReservationDto> getCancleReservations() {
		return cancleReservations;
	}

	public void setCancleReservations(List<MyReservationDto> cancleReservations) {
		this.cancleReservations = cancleReservations;
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
		return "MyReservationResponseDto [todoReservations=" + todoReservations + ", doneReservations="
			+ doneReservations + ", cancleReservations=" + cancleReservations + ", totalCount=" + totalCount
			+ ", todoCount=" + todoCount + ", doneCount=" + doneCount + ", cancleCount=" + cancleCount + "]";
	}
}
