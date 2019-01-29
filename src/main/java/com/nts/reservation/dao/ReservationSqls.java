/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

public class ReservationSqls {
	private static final String PRODUCT_TABLE = "product "
		+ "INNER JOIN display_info "
		+ "ON product.id = display_info.product_id "
		+ "INNER JOIN product_image "
		+ "ON product.id = product_image.product_id "
		+ "INNER JOIN file_info "
		+ "ON file_id = file_info.id ";
	private static final String PRODUCT_COULMN = "display_info.id as display_info_id, "
		+ "place_name, "
		+ "content as product_content, "
		+ "description as product_description, "
		+ "product.id as product_id, "
		+ "save_file_name as product_image_url ";

	public static final String LIMIT = "limit :start, 4 ";

	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM " + PRODUCT_TABLE
		+ "GROUP BY type "
		+ "HAVING type = 'th'";
	public static final String COUNT_BY_CATEGORY_ID = "SELECT count(*) FROM " + PRODUCT_TABLE
		+ "GROUP BY type, category_id "
		+ "HAVING type = 'th' "
		+ "and category_id = :categoryId";

	public static final String GET_PRODUCTS = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "GROUP BY display_info.id ";
	public static final String GET_PRODUCTS_BY_CATEGORY_ID = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "GROUP BY display_info.id, category_id "
		+ "HAVING category_id = :categoryId ";

	public static final String GET_PROMOTION_PRODUCTS = "SELECT " + PRODUCT_COULMN + "FROM " + PRODUCT_TABLE
		+ "INNER JOIN promotion "
		+ "ON promotion.product_id = product.id "
		+ "GROUP BY display_info.id ";
	public static final String COUNT_PROMOTION = "SELECT count(*) FROM promotion";

	public static final String GET_CATEGORIES = "SELECT category.id, name, count(*) as count "
		+ "FROM category "
		+ "INNER JOIN product "
		+ "ON category.id = product.category_id "
		+ "INNER JOIN display_info "
		+ "ON product.id = display_info.product_id "
		+ "GROUP BY category.id";
}