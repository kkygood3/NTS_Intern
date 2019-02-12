/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao.sqls;

public class ProductDaoSqls {
	public static final String GET_PRODUCTS_BY_CATEGORY = 
		"SELECT display_info.id as 'displayInfoId', place_name, product.content as 'productContent', product.description as 'productDescription', product.id as 'productId', IF(product_image.type = 'th', file_info.file_name, null) as 'productImageUrl' " + 
		"FROM category " + 
		"INNER JOIN product ON product.category_id = category.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"INNER JOIN product_image ON product_image.product_id = product.id " + 
		"INNER JOIN file_info ON file_info.id = product_image.file_id " + 
		"WHERE category.id = :categoryId " + 
		"GROUP BY display_info.id " + 
		"LIMIT :start, :limit";

	public static final String GET_ALL_PRODUCTS = 
		"SELECT display_info.id as 'displayInfoId', place_name, product.content as 'productContent', product.description as 'productDescription', product.id as 'productId', IF(product_image.type = 'th', file_info.file_name, null) as 'productImageUrl' " + 
		"FROM category " + 
		"INNER JOIN product ON product.category_id = category.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"INNER JOIN product_image ON product_image.product_id = product.id " + 
		"INNER JOIN file_info ON file_info.id = product_image.file_id " + 
		"GROUP BY display_info.id " + 
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
	
	public static final String GET_PRODUCT_IMAGES = "SELECT file_info.content_type 'contentType', file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', file_info.id 'fileInfoId', file_info.file_name 'fileName', file_info.modify_date 'modifyDate', product.id 'productId', product_image.id 'productImageId', file_info.save_file_name 'saveFileName', product_image.type 'type' " + 
		"FROM product " + 
		"INNER JOIN product_image ON product_image.product_id = product.id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"INNER JOIN file_info ON file_info.id = product_image.file_id " + 
		"WHERE display_info.id = :displayInfoId " + 
		"AND product_image.type = 'ma'";
	
	public static final String GET_PRODUCT_PRICES = "SELECT product_price.id 'productPriceId', product.id 'productId', price_type_name 'priceTypeName', price 'price', discount_rate 'discountRate', product_price.create_date 'createDate', product_price.modify_date 'modifyDate' " + 
		"FROM product_price " + 
		"INNER JOIN product ON product.id = product_price.product_id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"WHERE display_info.id = :displayInfoId";
}
