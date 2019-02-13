/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.product.dao.queries;

public class ProductQueries {
	public static final String SELECT_PROUDUCT_PAGE =
		"SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url " +
		"FROM product " +
		"INNER JOIN product_image ON product.id = product_image.product_id " +
		"INNER JOIN display_info ON product.id = display_info.product_id " +
		"INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th' " +
		"LIMIT :start, :limit;";

	public static final String SELECT_PROUDUCT_PAGE_BY_CATEGORY =
		"SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url " +
		"FROM product " +
		"INNER JOIN product_image ON product.id = product_image.product_id " +
		"INNER JOIN display_info ON product.id = display_info.product_id " +
		"INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th' " +
		"WHERE category_id = :categoryId " +
		"LIMIT :start, :limit;";

	public static final String SELECT_PRODUCT_COUNT =
		"SELECT COUNT(display_info.id) FROM product " +
		"INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th' " +
		"INNER JOIN display_info ON product.id = display_info.product_id";

	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY =
		"SELECT COUNT(display_info.id) FROM product " +
		"INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th' " +
		"INNER JOIN display_info ON product.id = display_info.product_id " +
		"WHERE category_id = :categoryId";

	public static final String SELECT_PROUDUCT_IMAGE =
		"SELECT file_info.content_type AS content_type, file_info.create_date AS create_date, file_info.delete_flag AS delete_flag, " +
		"file_info.id AS file_info_id, file_info.file_name AS file_name, file_info.modify_date AS modify_date, product_image.product_id AS product_id, " +
		"product_image.id AS product_image_id, file_info.save_file_name AS save_file_name, product_image.type AS type " +
		"FROM product_image " +
		"INNER JOIN file_info ON file_info.id = product_image.file_id " +
		"INNER JOIN product ON product_image.product_id = product.id " +
		"INNER JOIN display_info ON display_info.product_id = product.id " +
		"WHERE display_info.id = :displayInfoId;";

	public static final String SELECT_PROUDUCT_PRICE =
		"SELECT product_price.create_date AS create_date, product_price.discount_rate AS discount_rate, product_price.modify_date AS modify_date, " +
		"product_price.price AS price, product_price.price_type_name AS price_type_name, product_price.product_id AS product_id, product_price.id AS product_price_id " +
		"FROM product_price " +
		"INNER JOIN product ON product.id = product_price.product_id " +
		"INNER JOIN display_info ON display_info.product_id = product.id " +
		"WHERE display_info.id = :displayInfoId;";
}
