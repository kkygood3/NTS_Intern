/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dto.queries;

public class MainPromotionQueries {
	public static final String SELECT_PROMOTION_COUNT = "SELECT count(product_id) FROM promotion_api_view";
	public static final String SELECT_PROMOTION = "SELECT display_info_id, place_name, product_content, product_description, product_id, product_image_url FROM promotion_api_view order by display_info_id desc limit :limit";
}
