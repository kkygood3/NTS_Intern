/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class PromotionResponse {
	private List<Promotion> items;

	public List<Promotion> getItems() {
		return items;
	}

	public void setItems(List<Promotion> items) {
		this.items = items;
	}
}
