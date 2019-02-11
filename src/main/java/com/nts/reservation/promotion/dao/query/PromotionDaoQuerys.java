/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class PromotionDaoQuerys {
	public static final String SELECT_PROMOTION = "SELECT display_info.id AS display_info_id, display_info.place_name, product.content AS product_content, product.description AS product_description, product.id AS product_id, file_info.save_file_name AS product_image_url FROM promotion"
		+ " INNER JOIN display_info ON promotion.product_id = display_info.product_id"
		+ " INNER JOIN product ON promotion.product_id = product.id"
		+ " INNER JOIN product_image ON promotion.product_id = product_image.product_id AND product_image.type = 'th'"
		+ " INNER JOIN file_info ON product_image.file_id = file_info.id"
		+ " GROUP BY promotion.product_id"
		+ " LIMIT :limit";

	public static final String SELECT_PROMOTION_COUNT = "SELECT COUNT(display_info.id)"
		+ " FROM promotion"
		+ " INNER JOIN display_info ON promotion.product_id = display_info.product_id";
}
