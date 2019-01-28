/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

public class ProductDaoSqls {
	private static final String PRODUCT_TABLE = "product, display_info, product_image, file_info "
		+ "WHERE product.id = display_info.product_id "
		+ "and product.id = product_image.product_id "
		+ "and file_id = file_info.id "
		+ "and type = 'th' ";
	private static final String PRODUCT_COULMN = "SELECT display_info.id as display_info_id, "
		+ "place_name, "
		+ "content as product_content, "
		+ "description as product_description, "
		+ "product.id as product_id, "
		+ "save_file_name as product_image_url ";

	public static final String LIMIT = "limit :start, 4 ";

	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM " + PRODUCT_TABLE;
	public static final String COUNT_BY_CATEGORY_ID = SELECT_COUNT_ALL + "and category_id = :categoryId";

	public static final String GET_PRODUCTS = PRODUCT_COULMN + "FROM " + PRODUCT_TABLE;
	public static final String GET_PRODUCTS_BY_CATEGORY_ID = GET_PRODUCTS + "and category_id = :categoryId ";

	public static final String GET_PROMOTION_PRODUCTS = PRODUCT_COULMN + "FROM promotion, " + PRODUCT_TABLE
		+ "and promotion.product_id = product.id ";
	public static final String COUNT_PROMOTION = "SELECT count(*) FROM promotion";

}