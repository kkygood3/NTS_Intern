/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dto.queries;

public class ProductQueries {
	public static final String PRODUCT_SELECT_ALL = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product ORDER BY id DESC";
	public static final String PRODUCT_SELECT_BY_ID = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product where id = :id";
	public static final String PRODUCT_SELECT_PAGING = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product ORDER BY id DESC limit :start, :limit";
	public static final String PRODUCT_SELECT_COUNT = "SELECT count(id) FROM product";
}
