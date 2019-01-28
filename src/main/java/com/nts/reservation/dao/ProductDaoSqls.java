/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

/*
 * @author 육성렬
 */
public class ProductDaoSqls {
	public static final String SELECT_PRODUCT = "SELECT display_info.id AS display_info_id, product.id AS product_id"
		+ ", product.content AS product_content, product.description AS product_description"
		+ ", display_info.place_name AS place_name, file_info.save_file_name AS product_image_url"
		+ " FROM file_info, product INNER JOIN display_info ON product.id = display_info.product_id"
		+ " LEFT JOIN product_image ON product.id = product_image.product_id AND product_image.type = 'th'"
		+ " WHERE product_image.file_id = file_info.id ORDER BY display_info.id ASC LIMIT :start, :limit";

	public static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT display_info.id AS display_info_id, product.id AS product_id, product.content AS product_content"
		+ ", product.description AS product_description, display_info.place_name AS place_name, file_info.save_file_name AS product_image_url"
		+ " FROM product INNER JOIN display_info ON product.id = display_info.product_id LEFT JOIN product_image"
		+ " ON product.id = product_image.product_id AND product_image.type = 'th' INNER JOIN file_info ON product_image.file_id = file_info.id"
		+ " WHERE product.category_id = :category ORDER BY display_info.id ASC LIMIT :start, :limit";

	public static final String SELECT_COUNT_PRODUCT = "SELECT COUNT(*) FROM product INNER JOIN display_info ON product.id = display_info.product_id";

	public static final String SELECT_COUNT_PRODUCT_BY_CATEGORY = "SELECT COUNT(*) FROM product"
		+ " INNER JOIN display_info ON product.id = display_info.product_id WHERE product.category_id = :category";

	public static final String SELECT_PROMOTION_PRODUCT = "SELECT display_info.id AS display_info_id, product.id AS product_id, product.content AS product_content"
		+ ", product.description AS product_description, display_info.place_name AS place_name, file_info.save_file_name AS product_image_url"
		+ " FROM promotion INNER JOIN display_info ON promotion.product_id = display_info.product_id INNER JOIN product ON product.id = promotion.product_id"
		+ " LEFT JOIN product_image ON product_image.product_id = promotion.product_id AND product_image.type = 'th'"
		+ " INNER JOIN file_info ON file_info.id = product_image.file_id";
}
