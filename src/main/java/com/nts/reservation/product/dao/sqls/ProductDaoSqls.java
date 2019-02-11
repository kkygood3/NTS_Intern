/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao.sqls;

public class ProductDaoSqls {
	public static final String GET_PRODUCTS_BY_CATEGORY = 
		"SELECT display_info.id as 'displayInfoId', place_name as 'placeName', product.content as 'productContent', product.description as 'productDescription', product.id as 'productId', file_info.file_name as 'productImageUrl' " + 
		"FROM category " + 
		"INNER JOIN product ON product.category_id = category.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"INNER JOIN product_image ON product_image.product_id = product.id " + 
		"INNER JOIN file_info ON file_info.id = product_image.file_id " + 
		"WHERE category.id = :categoryId " + 
		"AND product_image.type = 'th'" + 
		"LIMIT :start, :limit";

	public static final String GET_ALL_PRODUCTS = 
		"SELECT display_info.id as 'displayInfoId', place_name as 'placeName', product.content as 'productContent', product.description as 'productDescription', product.id as 'productId', file_info.file_name as 'productImageUrl' " + 
		"FROM category " + 
		"INNER JOIN product ON product.category_id = category.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"INNER JOIN product_image ON product_image.product_id = product.id " + 
		"INNER JOIN file_info ON file_info.id = product_image.file_id " + 
		"WHERE product_image.type = 'th'" + 
		"LIMIT :start, :limit";

	public static final String GET_ALL_PRODUCTS_COUNT = 
		"SELECT count(category_id) as 'count' " + 
		"FROM category " + 
		"INNER JOIN product ON product.category_id = category.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id";

	public static final String GET_PRODUCTS_COUNT_BY_CATEGORY = 
		"SELECT count(category_id) as 'count' " + 
		"FROM category " + 
		"INNER JOIN product ON product.category_id = category.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"GROUP BY category_id " + 
		"HAVING category_id = :categoryId";
}
