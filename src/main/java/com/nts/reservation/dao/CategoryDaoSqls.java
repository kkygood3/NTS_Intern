/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

/*
 * @author 육성렬
 */
public class CategoryDaoSqls {
	public static final String SELECT_CATEGORY = "SELECT category.id, category.name, COUNT(*) AS count FROM category"
		+ " INNER JOIN product ON category.id = product.category_id INNER JOIN display_info ON product.id = display_info.product_id GROUP BY category.name ORDER BY id";
}
