/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.querys;

public class ProductQuerys {
	public static final String SELECT_PRODUCT_LIST = "select p.id as product_id, "
		+ "p.description as product_description, p.content as product_content, "
		+ "d.id as display_info_id, d.place_name as place_name, "
		+ "f.save_file_name as product_image_url "
		+ "from product p, display_info d, product_image i, file_info f "
		+ "where p.id = d.product_id and p.id = i.product_id and i.file_id = f.id "
		+ "and p.category_id like concat('%', :categoryId, '%') and i.type = 'th'"
		+ "limit :start, 4";

	public static final String SELECT_CATEGORY_PRODUCTS_COUNT = "select count(p.id) "
		+ "from product p, display_info d "
		+ "where p.id = d.product_id and p.category_id like concat('%', :categoryId, '%')";
}
