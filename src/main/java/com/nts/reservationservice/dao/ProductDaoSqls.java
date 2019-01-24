/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

public class ProductDaoSqls {
	public static final String SELECT_PRODUCT_PAGING = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product ORDER BY create_date DESC limit :start, :limit";
	public static final String SELECT_PRODUCT_PAGING_BY_CATEGORY = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product WHERE category_id = :category ORDER BY create_date DESC limit :start, :limit";
	public static final String SELECT_COUNT_PRODUCT = "SELECT count(*) FROM product";
	public static final String SELECT_COUNT_PRODUCT_BY_CATEGORY = "SELECT count(*) WHERE category_id = :category FROM product";
}
