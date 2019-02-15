/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class HomeCategorySqls {
	public static final String SELECT_CATEGORIES = 
			"SELECT category.id, category.name, COUNT(display_info.id) AS count" + 
			"  FROM category" + 
			"  INNER JOIN product ON category.id = product.category_id" + 
			"  INNER JOIN display_info ON product.id = display_info.product_id" + 
			"  GROUP BY category.id" + 
			"  LIMIT :pagingLimit";
}
