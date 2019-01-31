/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dto.category;

import java.util.List;

/**
 * @author 전연빈
 */
public class Categories {
	private List<Category> items;

	public Categories(List<Category> items) {
		this.items = items;
	}

	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}

}
