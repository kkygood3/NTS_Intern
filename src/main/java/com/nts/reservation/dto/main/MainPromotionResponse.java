/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dto.main;

import java.util.List;

/**
 * 메인 페이지 로드시 /api/promotions 출력 객체 
 */
public class MainPromotionResponse {
	private List<MainPromotion> promotionList;
	private Integer count;

	public MainPromotionResponse(List<MainPromotion> promotionList, Integer count) {
		this.promotionList = promotionList;
		this.count = count;
	}

	public List<MainPromotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<MainPromotion> promotionList) {
		this.promotionList = promotionList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
