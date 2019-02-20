/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.response;

import java.util.List;

import com.nts.reservation.dto.ReservationDisplayInfoDto;

/**
 * 나의 예약정보 response용 DTO
 * @author jinwoo.bae
 */
public class MyReservationResponseDto {
	private List<ReservationDisplayInfoDto> todoReservationDisplayInfos; // 이용예정 예약들
	private List<ReservationDisplayInfoDto> doneReservationDisplayInfos; // 이용완료 예약들
	private List<ReservationDisplayInfoDto> cancleReservationDisplayInfos; // 취소된 예약들
	private int totalCount; // 전체 개수
	private int todoCount; // 이용 예정 개수
	private int doneCount; // 이용 완료 개수
	private int cancleCount; // 취소 개수

	public List<ReservationDisplayInfoDto> getTodoReservationDisplayInfos() {
		return todoReservationDisplayInfos;
	}

	public void setTodoReservationDisplayInfos(List<ReservationDisplayInfoDto> todoReservationDisplayInfos) {
		this.todoReservationDisplayInfos = todoReservationDisplayInfos;
	}

	public List<ReservationDisplayInfoDto> getDoneReservationDisplayInfos() {
		return doneReservationDisplayInfos;
	}

	public void setDoneReservationDisplayInfos(List<ReservationDisplayInfoDto> doneReservationDisplayInfos) {
		this.doneReservationDisplayInfos = doneReservationDisplayInfos;
	}

	public List<ReservationDisplayInfoDto> getCancleReservationDisplayInfos() {
		return cancleReservationDisplayInfos;
	}

	public void setCancleReservationDisplayInfos(List<ReservationDisplayInfoDto> cancleReservationDisplayInfos) {
		this.cancleReservationDisplayInfos = cancleReservationDisplayInfos;
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

}
