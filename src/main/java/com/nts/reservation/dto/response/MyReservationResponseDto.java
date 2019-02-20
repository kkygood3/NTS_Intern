/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.MyReservationDto;

/**
 * 나의 예약정보 response용 DTO
 * @author jinwoo.bae
 */
public class MyReservationResponseDto {
	private List<MyReservationDto> todoMyReservations; // 이용예정 예약들
	private List<MyReservationDto> doneMyReservations; // 이용완료 예약들
	private List<MyReservationDto> cancleMyReservations; // 취소된 예약들
	private int totalCount; // 전체 개수
	private int todoCount; // 이용 예정 개수
	private int doneCount; // 이용 완료 개수
	private int cancleCount; // 취소 개수

	public List<MyReservationDto> getTodoMyReservations() {
		return todoMyReservations;
	}

	public void setTodoMyReservations(List<MyReservationDto> todoMyReservations) {
		this.todoMyReservations = todoMyReservations;
	}

	public List<MyReservationDto> getDoneMyReservations() {
		return doneMyReservations;
	}

	public void setDoneMyReservations(List<MyReservationDto> doneMyReservations) {
		this.doneMyReservations = doneMyReservations;
	}

	public List<MyReservationDto> getCancleMyReservations() {
		return cancleMyReservations;
	}

	public void setCancleMyReservations(List<MyReservationDto> cancleMyReservations) {
		this.cancleMyReservations = cancleMyReservations;
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
		return "MyReservationResponseDto [todoMyReservations=" + todoMyReservations + ", doneMyReservations="
			+ doneMyReservations + ", cancleMyReservations=" + cancleMyReservations + ", totalCount=" + totalCount
			+ ", todoCount=" + todoCount + ", doneCount=" + doneCount + ", cancleCount=" + cancleCount
			+ ", getTodoMyReservations()=" + getTodoMyReservations() + ", getDoneMyReservations()="
			+ getDoneMyReservations() + ", getCancleMyReservations()=" + getCancleMyReservations()
			+ ", getTotalCount()=" + getTotalCount() + ", getTodoCount()=" + getTodoCount() + ", getDoneCount()="
			+ getDoneCount() + ", getCancleCount()=" + getCancleCount() + ", getClass()=" + getClass() + ", hashCode()="
			+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
