/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class MainProductSqls {
	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = 
			"SELECT COUNT(display_info.id) FROM product" + 
			" INNER JOIN display_info ON product.id = display_info.product_id" + 
			" WHERE product.category_id = :categoryId";

	public static final String SELECT_PRODUCT_COUNT = 
			"SELECT COUNT(display_info.id) FROM product" + 
			" INNER JOIN display_info ON product.id = display_info.product_id";

	public static final String SELECT_PRODUCT_PAGE_BY_CATEGORY = 
			"SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url" + 
			" FROM product" + 
			" INNER JOIN display_info ON product.id = display_info.product_id" + 
			" INNER JOIN product_image ON product.id = product_image.product_id AND product_image.type='th'" + 
			" INNER JOIN file_info ON product_image.file_id = file_info.id" + 
			" WHERE category_id = :categoryId" + 
			" LIMIT :start, :pagingLimit";

	public static final String SELECT_PRODUCT_PAGE = 
			"SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url" + 
			" FROM product" + 
			" INNER JOIN display_info ON product.id = display_info.product_id" + 
			" INNER JOIN product_image ON product.id = product_image.product_id AND product_image.type='th'" + 
			" INNER JOIN file_info ON product_image.file_id = file_info.id" + 
			" LIMIT :start, :pagingLimit";
}
