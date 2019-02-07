/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dto.promotion;

import java.util.List;

/**
 * @author 전연빈
 */
public class Promotions {
	private List<Promotion> items;

	public Promotions(List<Promotion> items) {
		this.items = items;
	}

	public List<Promotion> getItems() {
		return items;
	}

	public void setItems(List<Promotion> items) {
		this.items = items;
	}

}
