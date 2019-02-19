/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.product.dao.querys;

public class ProductQuerys {

	private static final int LIMIT_COUNT = 4;

	public static final String SELECT_ALL_CATEGORY_PRODUCT_LIST = "select p.id as product_id"
		+ ", p.description as product_description"
		+ ", p.content as product_content"
		+ ", d.id as display_info_id"
		+ ", d.place_name as place_name"
		+ ", f.save_file_name as product_image_url "
		+ "from product p "
		+ "inner join display_info d "
		+ "on p.id = d.product_id "
		+ "inner join product_image i "
		+ "on p.id = i.product_id "
		+ "inner join file_info f "
		+ "on i.file_id = f.id "
		+ "where i.type = 'th' "
		+ "limit :start, " + LIMIT_COUNT;

	public static final String SELECT_ALL_CATEGORY_PRODUCTS_COUNT = "select count(*) "
		+ "from product p "
		+ "inner join display_info d "
		+ "on p.id = d.product_id";

	public static final String SELECT_ONE_CATEGORY_PRODUCT_LIST = "select p.id as product_id"
		+ ", p.description as product_description"
		+ ", p.content as product_content"
		+ ", d.id as display_info_id"
		+ ", d.place_name as place_name"
		+ ", f.save_file_name as product_image_url "
		+ "from product p "
		+ "inner join display_info d "
		+ "on p.id = d.product_id "
		+ "inner join product_image i "
		+ "on p.id = i.product_id "
		+ "inner join file_info f "
		+ "on i.file_id = f.id "
		+ "where p.category_id = :categoryId "
		+ "and i.type = 'th' "
		+ "limit :start, " + LIMIT_COUNT;

	public static final String SELECT_ONE_CATEGORY_PRODUCTS_COUNT = "select count(*) "
		+ "from product p "
		+ "inner join display_info d "
		+ "on p.id = d.product_id "
		+ "where p.category_id = :categoryId";
}
