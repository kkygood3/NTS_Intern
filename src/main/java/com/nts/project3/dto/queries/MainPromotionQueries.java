/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.project3.dto.queries;

public class MainPromotionQueries {
	public static final String SELECT_PROMOTION_COUNT = 
			"SELECT COUNT(display_info.id)" + "FROM promotion"
			+ " INNER JOIN display_info ON promotion.product_id = display_info.product_id";

	public static final String SELECT_PROMOTION = 
			"SELECT display_info.id AS display_info_id, display_info.place_name,product.content AS product_countent, product.description AS product_description, product.id AS product_id, file_info.save_file_name FROM promotion"
			+ " INNER JOIN display_info ON promotion.product_id = display_info.product_id"
			+ " INNER JOIN product ON promotion.product_id = product.id"
			+ " INNER JOIN product_image ON promotion.product_id = product_image.product_id AND product_image.type = 'th'"
			+ " INNER JOIN file_info ON product_image.file_id = file_info.id"
			+ " ORDER BY display_info.id DESC"
			+ " LIMIT :limit";
}
