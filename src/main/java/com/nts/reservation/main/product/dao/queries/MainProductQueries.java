/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.main.product.dao.queries;

public class MainProductQueries {
	public static final String SELECT_PROUDUCT_PAGE = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url\r\n"
		+
		"FROM product\r\n" +
		"INNER JOIN product_image ON product.id = product_image.product_id\r\n" +
		"INNER JOIN display_info ON product.id = display_info.product_id\r\n" +
		"INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th'\r\n" +
		"LIMIT :start, :limit;";

	public static final String SELECT_PROUDUCT_PAGE_BY_CATEGORY = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url\r\n"
		+
		"FROM product\r\n" +
		"INNER JOIN product_image ON product.id = product_image.product_id\r\n" +
		"INNER JOIN display_info ON product.id = display_info.product_id\r\n" +
		"INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th'\r\n" +
		"WHERE category_id = :categoryId\r\n" +
		"LIMIT :start, :limit;";

	public static final String SELECT_PRODUCT_COUNT = "SELECT COUNT(display_info.id) FROM product\r\n" +
		"INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th'\r\n" +
		"INNER JOIN display_info ON product.id = display_info.product_id";

	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT COUNT(display_info.id) FROM product\r\n" +
		"INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th'\r\n" +
		"INNER JOIN display_info ON product.id = display_info.product_id\r\n" +
		"WHERE category_id = :categoryId";
}
