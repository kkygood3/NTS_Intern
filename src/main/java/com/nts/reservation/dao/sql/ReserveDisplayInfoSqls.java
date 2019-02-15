/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class ReserveDisplayInfoSqls {
	public static final String SELECT_RESERVE____ = 
		"SELECT display_info.id, display_info.opening_hours, display_info.place_name, display_info.place_street, file_info.save_file_name AS product_image" + 
		" FROM display_info" + 
		" INNER JOIN product_image ON display_info.product_id = product_image.product_id AND product_image.type = 'th'" + 
		" INNER JOIN file_info ON product_image.file_id = file_info.id";
}
