/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

public class ProductDaoSqls {
	//	public static final String SELECT_PRODUCT = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product ORDER BY create_date DESC limit :start, :limit";
	//	public static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product WHERE category_id = :category ORDER BY create_date DESC limit :start, :limit";
	//	public static final String SELECT_COUNT_PRODUCT = "SELECT count(*) FROM product";
	//	public static final String SELECT_COUNT_PRODUCT_BY_CATEGORY = "SELECT count(*) FROM product WHERE category_id = :category";
	public static final String SELECT_PRODUCT_BY_ID = "SELECT id, category_id, description, content, event, create_date, modify_date FROM product WHERE id = :id";

	public static final String SELECT_PRODUCT = "SELECT display_info.id AS display_info_id, product.id AS product_id"
		+ ", product.content AS product_content, product.description AS product_description"
		+ ", display_info.place_name AS place_name, file_info.save_file_name AS product_image_url"
		+ " FROM file_info, product RIGHT JOIN display_info ON product.id = display_info.product_id"
		+ " INNER JOIN product_image ON product.id = product_image.product_id AND product_image.type = 'th'"
		+ " WHERE product_image.file_id = file_info.id ORDER BY display_info.id ASC LIMIT :start, :limit";

	public static final String SELECT_PRODUCT_BY_CATEGORY = "SELECT display_info.id AS display_info_id, product.id AS product_id"
		+ ", product.content AS product_content, product.description AS product_description"
		+ ", display_info.place_name AS place_name, file_info.save_file_name AS product_image_url"
		+ " FROM file_info, product RIGHT JOIN display_info ON product.id = display_info.product_id"
		+ " INNER JOIN product_image ON product.id = product_image.product_id AND product_image.type = 'th'"
		+ " WHERE product_image.file_id = file_info.id AND product.category_id = :category ORDER BY display_info.id ASC LIMIT :start, :limit";

	public static final String SELECT_COUNT_PRODUCT = "SELECT COUNT(*) FROM product RIGHT JOIN display_info ON product.id = display_info.product_id";
	public static final String SELECT_COUNT_PRODUCT_BY_CATEGORY = "SELECT COUNT(*) FROM product RIGHT JOIN display_info ON product.id = display_info.product_id WHERE product.category_id = :category";
}
