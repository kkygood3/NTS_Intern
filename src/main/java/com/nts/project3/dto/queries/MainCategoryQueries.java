/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dto.queries;

public class MainCategoryQueries {
	//public static final String SELECT_CATEGORIES = "SELECT id, name, count FROM category_api_view limit :limit";
	public static final String SELECT_CATEGORIES = 
		"select category.id, category.name, count_table.count"
		+ " from category,(select product.category_id,count(display_info.id) as count FROM display_info left join product on product.id = display_info.product_id group by product.category_id)count_table"
		+ " where category.id = count_table.category_id";
}
