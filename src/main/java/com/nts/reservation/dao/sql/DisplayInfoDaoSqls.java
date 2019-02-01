/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO_BY_ID = "SELECT display.product_id,"
		+ " display.id AS display_info_id,"
		+ " display.opening_hours,"
		+ " display.place_name,"
		+ " display.place_lot,"
		+ " display.place_street,"
		+ " display.tel AS telephone,"
		+ " display.homepage, display.email,"
		+ " display.create_date,"
		+ " display.modify_date,"
		+ " product.content AS product_content,"
		+ " product.event AS product_event,"
		+ " product.description AS product_description,"
		+ " category.id AS category_id,"
		+ " category.name AS category_name"
		+ " FROM display_info AS display, product, category"
		+ " WHERE display.id = :id"
		+ " AND product.id = display.product_id"
		+ " AND product.category_id = category.id";
}
