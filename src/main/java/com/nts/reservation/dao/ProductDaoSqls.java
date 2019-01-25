/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM product ";
	public static final String SELECT_BY_CATEGORY_ID = "SELECT a.id as id, description, place_name, content  FROM product as a Join category as b On a.category_id = b.id Join display_info as c On a.id = c.product_id where category_id = :id";
}
