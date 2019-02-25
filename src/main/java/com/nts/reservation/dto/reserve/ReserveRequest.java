/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

import java.util.List;

import com.nts.reservation.common.ReservationValidatior;
import com.nts.reservation.property.CommonProperties;

public class ReserveRequest {
	private Integer id;
	private String name;
	private String telephone;
	private String email;
	private Integer displayInfoId;
	private String reservationDate;
	private List<ReservePrice> reservePriceInfoList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getDisplayInfoId() {
		return displayInfoId;
	}

	public void setDisplayInfoId(Integer displayInfoId) {
		this.displayInfoId = displayInfoId;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	public List<ReservePrice> getReservePriceInfoList() {
		return reservePriceInfoList;
	}

	public void setReservePriceInfoList(List<ReservePrice> reservePriceInfoList) {
		this.reservePriceInfoList = reservePriceInfoList;
	}

	@Override
	public String toString() {
		return "ReserveRequest [name=" + name + ", telephone=" + telephone + ", email=" + email + ", displayInfoId="
			+ displayInfoId + ", reservationDate=" + reservationDate + ", reservePriceInfoList=" + reservePriceInfoList
			+ "]";
	}

	/**
	 * 예약 등록 정보 검증
	 * @return 티켓 수와 사용자 정보가 올바르다면 TRUE
	 */
	public boolean isValid() {
		int sumOfCount = 0;

		//티켓 정보가 없다.
		if (reservePriceInfoList.size() < 1) {
			return false;
		}

		//티켓 count가 0인 item이 존재한다.
		for (ReservePrice item : reservePriceInfoList) {
			if (item.getCount() < 1) {
				return false;
			}
			sumOfCount += item.getCount();
		}

		//티켓 count의 합이 MAX_TICKET_COUNT를 초과한다.
		if (sumOfCount > CommonProperties.MAX_TICKET_COUNT) {
			return false;
		}

		return ReservationValidatior.validateName(this.name) && ReservationValidatior.validateTelephone(this.telephone) && ReservationValidatior.validateEmail(this.email);
	}

}
