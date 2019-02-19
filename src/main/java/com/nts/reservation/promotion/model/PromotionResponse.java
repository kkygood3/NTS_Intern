/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.model;

import java.util.ArrayList;
import java.util.List;

public class PromotionResponse {

	private List<Promotion> promotionList;

	public PromotionResponse() {
		promotionList = new ArrayList<>();
	}

	public PromotionResponse(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

}
