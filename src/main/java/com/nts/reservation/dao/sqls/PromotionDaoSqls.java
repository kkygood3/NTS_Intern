/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sqls;

public class PromotionDaoSqls {
	public static final String GET_PROMOTION = "SELECT promotion.id as 'id', product.id as 'productId', file_info.file_name as 'productImageUrl' " + 
		"FROM product " + 
		"INNER JOIN promotion ON promotion.product_id = product.id " + 
		"INNER JOIN product_image ON product_image.product_id = product.id " + 
		"INNER JOIN file_info ON file_info.id = product_image.file_id " + 
		"WHERE product_image.type = 'th'";
}
