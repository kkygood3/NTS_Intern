/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class ProductDaoSqls {
	public static final String PRODUCT_COLUMNS = "display.id AS display_info_id"
		+ ", product.id AS product_id"
		+ ", product.content AS product_content"
		+ ", product.description AS product_description"
		+ ", display.place_name AS place_name"
		+ ", file.save_file_name AS product_image_url";

	public static final String SELECT_PRODUCT = "SELECT " + PRODUCT_COLUMNS
		+ " FROM file_info AS file, product"
		+ " INNER JOIN display_info AS display"
		+ " ON product.id = display.product_id"
		+ " INNER JOIN product_image"
		+ " ON product.id = product_image.product_id"
		+ " AND product_image.type = 'th'"
		+ " WHERE product_image.file_id = file.id"
		+ " ORDER BY display.id ASC"
		+ " LIMIT :start, :limit";

	public static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT " + PRODUCT_COLUMNS
		+ " FROM product"
		+ " INNER JOIN display_info AS display"
		+ " ON product.id = display.product_id"
		+ " INNER JOIN product_image"
		+ " ON product.id = product_image.product_id"
		+ " AND product_image.type = 'th'"
		+ " INNER JOIN file_info AS file"
		+ " ON product_image.file_id = file.id"
		+ " WHERE product.category_id = :category"
		+ " ORDER BY display.id ASC"
		+ " LIMIT :start, :limit";

	public static final String SELECT_COUNT_PRODUCT = "SELECT COUNT(display.id) FROM product"
		+ " INNER JOIN display_info AS display"
		+ " ON product.id = display.product_id";

	public static final String SELECT_COUNT_PRODUCT_BY_CATEGORY = "SELECT COUNT(display.id) FROM product"
		+ " INNER JOIN display_info AS display"
		+ " ON product.id = display.product_id"
		+ " WHERE product.category_id = :category";

	public static final String SELECT_PROMOTION_PRODUCT = "SELECT " + PRODUCT_COLUMNS
		+ " FROM promotion"
		+ " INNER JOIN display_info AS display"
		+ " ON promotion.product_id = display.product_id"
		+ " INNER JOIN product"
		+ " ON product.id = promotion.product_id"
		+ " INNER JOIN product_image"
		+ " ON product_image.product_id = promotion.product_id"
		+ " AND product_image.type = 'th'"
		+ " INNER JOIN file_info AS file"
		+ " ON file.id = product_image.file_id";
}
