/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_COUNT_ALL = "SELECT count(*) FROM product ";
	public static final String COUNT_BY_CATEGORY_ID = SELECT_COUNT_ALL
		+ "JOIN category ON product.category_id = category.id GROUP BY category.id HAVING category.id = :categoryId";
	public static final String GET_PRODUCTS = "SELECT display_info.id as display_info_id, "
		+ "place_name, "
		+ "content as product_content, "
		+ "description as product_description, "
		+ "product.id as product_id, "
		+ "save_file_name as product_image_url "
		+ "FROM product, display_info, product_image, file_info "
		+ "WHERE product.id = display_info.product_id "
		+ "and product.id = product_image.product_id "
		+ "and file_id = file_info.id "
		+ "and type = 'th' "
		+ "and category_id = :categoryId "
		+ "limit :start, 4 ";
}
