/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dto.queries;

public class MainProductQueries {
	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT count(product_id) FROM product_api_view where category_id = :categoryId";
	
	public static final String SELECT_PRODUCT_COUNT = "SELECT count(product_id) FROM product_api_view";
	
	public static final String SELECT_PRODUCT_PAGE_BY_CATEGORY = "select display_info.id as display_info_id, display_info.place_name, product.content as product_content, product.description as product_description, product.id as product_id, file_info.save_file_name as product_image_url\r\n" + 
		"FROM product\r\n" + 
		"left join product_image on product.id = product_image.product_id\r\n" + 
		"left join display_info on product.id = display_info.product_id\r\n" + 
		"inner join file_info on product_image.file_id = file_info.id and product_image.type='th'\r\n" + 
		"where category_id = :categoryId ORDER BY display_info_id DESC limit :start, :limit";
	
	public static final String SELECT_PRODUCT_PAGE = "SELECT category_id, display_info_id, place_name, product_content, product_description, product_id, product_image_url FROM product_api_view ORDER BY display_info_id DESC limit :start, :limit";
}
