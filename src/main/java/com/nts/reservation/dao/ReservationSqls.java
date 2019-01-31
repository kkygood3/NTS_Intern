/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

/**
* @author  : 이승수
*/
public class ReservationSqls {
	private static final String PRODUCT_TABLE = "product "
		+ "INNER JOIN display_info "
		+ "ON product.id = display_info.product_id "
		+ "INNER JOIN product_image "
		+ "ON product.id = product_image.product_id "
		+ "INNER JOIN file_info "
		+ "ON file_id = file_info.id ";
	private static final String PRODUCT_COULMN = "display_info.id AS display_info_id"
		+ ", place_name"
		+ ", content AS product_content"
		+ ", description AS product_description"
		+ ", product.id AS product_id"
		+ ", save_file_name AS product_image_url ";

	public static final String LIMIT = "LIMIT :start, 4 ";

	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM " + PRODUCT_TABLE + "WHERE type = 'th'";
	public static final String COUNT_BY_CATEGORY_ID = SELECT_COUNT_ALL + "AND category_id = :categoryId ";

	public static final String GET_PRODUCTS = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "WHERE type = 'th'";
	public static final String GET_PRODUCTS_BY_CATEGORY_ID = GET_PRODUCTS + "AND category_id = :categoryId ";

	public static final String GET_PROMOTION_PRODUCTS = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "INNER JOIN promotion "
		+ "ON promotion.product_id = product.id "
		+ "WHERE type = 'th'";
	public static final String COUNT_PROMOTION = "SELECT count(*) FROM " + PRODUCT_TABLE
		+ "INNER JOIN promotion "
		+ "ON promotion.product_id = product.id "
		+ "WHERE type = 'th'";

	public static final String GET_CATEGORIES = "SELECT category.id, name, count(*) AS count "
		+ "FROM category "
		+ "INNER JOIN product "
		+ "ON category.id = product.category_id "
		+ "INNER JOIN display_info "
		+ "ON product.id = display_info.product_id "
		+ "GROUP BY category.id ";
}