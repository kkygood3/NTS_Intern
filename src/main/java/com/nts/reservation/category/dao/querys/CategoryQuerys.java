/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.category.dao.querys;

public class CategoryQuerys {

	public static final String SELECT_CATEGORY_LIST = "select c.id as id"
		+ ", c.name as name"
		+ ", count(*) as count "
		+ "from category c "
		+ "inner join product p "
		+ "on c.id = p.category_id "
		+ "inner join display_info d "
		+ "on p.id = d.product_id "
		+ "group by c.id";
}
