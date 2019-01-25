/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.category.sqls;

public class CategorySqls {
	public static final String SELECT_CATEGORIES = 
		"SELECT c.id AS id, c.name AS name,COUNT(c.id) AS count " + 
		"FROM display_info d INNER JOIN product p INNER JOIN category c " + 
		"ON d.product_id = p.id AND p.category_id = c.id " + 
		"GROUP BY c.id";
}
