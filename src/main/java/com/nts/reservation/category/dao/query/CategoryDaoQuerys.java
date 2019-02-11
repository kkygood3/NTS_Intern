/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CategoryDaoQuerys {
	public static final String SELECT_CATEGORY = "SELECT category.id, category.name, count_table.count"
		+ " FROM category,"
		+ " (SELECT product.category_id,count(display_info.id) AS count FROM display_info left join product on product.id = display_info.product_id group by product.category_id)count_table"
		+ " WHERE category.id = count_table.category_id"
		+ " LIMIT :limit";
}
