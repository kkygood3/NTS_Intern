/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.queries;

public class MainPromotionQueries {
	public static final String SELECT_PROMOTION_PAGE = "SELECT promotion.id AS id, promotion.product_id AS product_id, file_info.save_file_name AS product_image_url\r\n"
		+
		"FROM promotion\r\n" +
		"INNER JOIN product ON promotion.product_id = product.id\r\n" +
		"INNER JOIN product_image ON promotion.product_id = product_image.product_id\r\n" +
		"INNER JOIN file_info ON product_image.file_id = file_info.id\r\n" +
		"WHERE product_image.type = 'th';";
}
