/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.promotion.dto;

import java.util.List;

/**
 * @author 전연빈
 */
public class PromotionResponse {
	private List<Promotion> items;

	public PromotionResponse(List<Promotion> items) {
		this.items = items;
	}

	public List<Promotion> getItems() {
		return items;
	}

}
