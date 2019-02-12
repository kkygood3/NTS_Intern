/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dto;

import java.util.List;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class PromotionItems {
	private int promotionCount;
	private List<Promotion> promotionList;

	public int getPromotionCount() {
		return promotionCount;
	}

	public void setPromotionCount(int promotionCount) {
		this.promotionCount = promotionCount;
	}

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}
}
