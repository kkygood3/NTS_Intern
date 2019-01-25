/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.model;

import java.util.ArrayList;
import java.util.List;

public class PromotionResponse {
	private List<Promotion> item;

	public PromotionResponse() {
		item = new ArrayList<>();
	}

	public PromotionResponse(List<Promotion> item) {
		this.item = item;
	}

	public List<Promotion> getItem() {
		return item;
	}

	public void setItem(List<Promotion> item) {
		this.item = item;
	}

}
