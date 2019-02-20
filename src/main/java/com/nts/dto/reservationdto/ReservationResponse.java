/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto.reservationdto;

import java.util.List;

public class ReservationResponse {
	private Reservation reservation;
	private List<ReservationPrice> prices;

	public ReservationResponse() {}

	public List<ReservationPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<ReservationPrice> prices) {
		this.prices = prices;
	}

	@Override
	public String toString() {
		return "ReservationResponse [reservation=" + reservation + ", prices=" + prices + "]";
	}
}
