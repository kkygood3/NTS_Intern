package com.nts.reservation.category.dao.sqls;

public class CategoryDaoSqls {
	public static final String GET_CATEGORY = "SELECT count(category_id) as 'count', category_id as 'id', name as 'name'" + 
		"FROM category " +
		"INNER JOIN product ON product.category_id = category.id " +
		"GROUP BY category_id";
}
