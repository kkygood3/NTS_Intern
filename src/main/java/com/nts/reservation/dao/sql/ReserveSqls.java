/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class ReserveSqls {
	public static final String SELECT_RESERVE_DISPLAY_INFO = 
		"SELECT" + 
		" display_info.id," + 
		" product.description AS product_description," + 
		" display_info.opening_hours," + 
		" display_info.place_name," + 
		" display_info.place_street," + 
		" file_info.save_file_name AS product_image" + 
		" FROM display_info" + 
		" INNER JOIN product ON display_info.product_id = product.id" +
		" INNER JOIN product_image ON display_info.product_id = product_image.product_id AND product_image.type = 'th'" + 
		" INNER JOIN file_info ON product_image.file_id = file_info.id" + 
		" WHERE display_info.id = :displayInfoId";
	
	public static final String SELECT_RESERVE_PRICE = 
		"SELECT" + 
		" product_price.price," + 
		" product_price.price_type_name," + 
		" ROUND(product_price.discount_rate,0) AS discount_rate" + 
		" FROM display_info" + 
		" INNER JOIN product_price ON display_info.product_id = product_price.product_id" + 
		" WHERE display_info.id = :displayInfoId";
}
