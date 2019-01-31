/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

/**
 * 카테고리 데이터에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class CategoryDaoSqls {
	public static final String SELECT_ALL_WITH_PRODUCT_COUNT = "SELECT  "
		+ "    c.id, c.name, COUNT(c.id) AS count "
		+ "FROM "
		+ "    category c, "
		+ "    product p, "
		+ "    display_info di "
		+ "WHERE "
		+ "    c.id = p.category_id "
		+ "        AND p.id = di.product_id "
		+ "GROUP BY c.id";
}