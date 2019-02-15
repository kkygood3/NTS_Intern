/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class HomePromotionSqls {
	public static final String SELECT_PROMOTION_COUNT = 
			"SELECT COUNT(display_info.id)" + 
			" FROM promotion" + 
			" INNER JOIN display_info ON promotion.product_id = display_info.product_id";

	public static final String SELECT_PROMOTION = 
			"SELECT display_info.id AS display_info_id, file_info.save_file_name AS product_image_url" + 
			" FROM promotion" + 
			" INNER JOIN display_info ON promotion.product_id = display_info.product_id" + 
			" INNER JOIN product_image ON promotion.product_id = product_image.product_id AND product_image.type = 'th'" + 
			" INNER JOIN file_info ON product_image.file_id = file_info.id" + 
			" GROUP BY promotion.product_id" + 
			" LIMIT :pagingLimit";
}
