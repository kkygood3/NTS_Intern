/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

/*
 * @author 육성렬
 */
public class CategoryDaoSqls {
	public static final String SELECT_CATEGORY = "SELECT id, name"
		+ ", (SELECT COUNT(*) FROM product RIGHT JOIN display_info ON product.id = display_info.product_id WHERE product.category_id = category.id)"
		+ " as count FROM category ORDER BY id";
}
