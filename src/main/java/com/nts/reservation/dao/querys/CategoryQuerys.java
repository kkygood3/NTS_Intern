/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.querys;

public class CategoryQuerys {

	public static final String SELECT_CATEGORY_LIST = "select c.id, c.name, count(*) as count "
		+ "from category c, display_info d, product p  "
		+ "where p.id = d.product_id and p.category_id = c.id "
		+ "group by c.id;";
}
