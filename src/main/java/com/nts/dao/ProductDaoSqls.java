/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

/**
 * 상품 테이블에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class ProductDaoSqls {
	public static final String SELECT_ALL = " SELECT"
		+ " 	display_info.id as displayInfoId,"
		+ " 	product.id as productId,"
		+ " 	product.description as productDescription,"
		+ " 	display_info.place_name as placeName,"
		+ " 	product.content as productContent,"
		+ " 	file_info.save_file_name as productImageUrl"
		+ " FROM"
		+ " 	product, product_image, display_info, display_info_image, file_info"
		+ " WHERE"
		+ " 	product_image.type = 'th'"
		+ " 	AND product.id = product_image.product_id"
		+ " 	AND product.id = display_info.product_id"
		+ " 	AND display_info.id = display_info_image.display_info_id"
		+ " 	AND file_info.id = product_image.file_id"
		+ " ORDER BY display_info.id ASC limit :start, :limit";
	public static final String SELECT_BY_CATEGORY_ID = " SELECT"
		+ " 	display_info.id as displayInfoId,"
		+ " 	product.id as productId,"
		+ " 	product.description as productDescription,"
		+ " 	display_info.place_name as placeName,"
		+ " 	product.content as productContent,"
		+ " 	file_info.save_file_name as productImageUrl"
		+ " FROM"
		+ " 	product, product_image, display_info, display_info_image, file_info"
		+ " WHERE"
		+ " 	category_id = :categoryId"
		+ " 	AND product_image.type = 'th'"
		+ " 	AND product.id = product_image.product_id"
		+ " 	AND product.id = display_info.product_id"
		+ " 	AND display_info.id = display_info_image.display_info_id"
		+ " 	AND file_info.id = product_image.file_id"
		+ " ORDER BY display_info.id ASC limit :start, :limit";
	public static final String SELECT_COUNT = "SELECT count(*) FROM product";
	public static final String SELECT_COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM product WHERE category_id = :categoryId";
}