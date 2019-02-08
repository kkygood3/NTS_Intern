/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.queries;

public class DisplayInfoQueries {
	public static final String SELECT_DISPLAY_INFO = 
		"SELECT product.category_id,category.name AS category_name,display_info.create_date,display_info.id AS display_info_id,display_info.email,display_info.homepage, display_info.modify_date, display_info.opening_hours, display_info.place_lot,display_info.place_name,product.content AS product_content, product.description AS product_description, product.event AS product_event, product.id AS product_id, display_info.tel AS telephone"
		+ " FROM display_info"
		+ " LEFT JOIN product ON product.id = display_info.product_id"
		+ " LEFT JOIN category ON product.category_id = category.id"
		+ " WHERE display_info.id = :displayInfoId";
}
