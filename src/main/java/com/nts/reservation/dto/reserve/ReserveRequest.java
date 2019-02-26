/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.reserve;

import java.util.List;

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
}
