/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dto.queries;

public class MainPageProductQueries {
	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT count(product_id) FROM product_api_view where category_id = :categoryId";
	public static final String SELECT_PRODUCT_COUNT = "SELECT count(product_id) FROM product_api_view";
	public static final String SELECT_PRODUCT_PAGE_BY_CATEGORY = "SELECT category_id, display_info_id, place_name, product_content, product_description, product_id, product_image_url FROM product_api_view where category_id = :categoryId ORDER BY display_info_id DESC limit :start, :limit";
	public static final String SELECT_PRODUCT_PAGE = "SELECT category_id, display_info_id, place_name, product_content, product_description, product_id, product_image_url FROM product_api_view ORDER BY display_info_id DESC limit :start, :limit";
}
