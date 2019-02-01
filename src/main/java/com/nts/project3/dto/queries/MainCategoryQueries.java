/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dto.queries;

public class MainCategoryQueries {
	public static final String SELECT_CATEGORIES = "SELECT id, name, count FROM category_api_view limit :limit";
}
