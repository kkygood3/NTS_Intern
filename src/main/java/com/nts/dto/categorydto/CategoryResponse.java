/*
 * Copyright 2019 by NAVER Corp. All rights reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.dto.categorydto;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
 

public class CategoryResponse {
	private List<Category> items;

	public CategoryResponse(List<Category> items) {
		this.items = items;
	}
	
	public List<Category> getItems() {
		return items;
	}

	public void setItems(List<Category> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		ToStringStyle style = ToStringStyle.MULTI_LINE_STYLE;
		ReflectionToStringBuilder tsb = new ReflectionToStringBuilder(this, style);
		return tsb.toString();
	}

}