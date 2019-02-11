/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.main.category.dao.queries;

public class MainCategoryQueries {
	public static final String SELECT_CATEGORY_PAGE = "SELECT category.id AS id, category.name AS name, count(product.category_id) AS count "+
		"FROM category " +
		"INNER JOIN product ON category.id = product.category_id " +
		"GROUP BY category.id;";
}
