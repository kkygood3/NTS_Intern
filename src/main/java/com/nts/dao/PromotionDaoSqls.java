/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.dao;

/**
 * @author jinwoo.bae
 *
 */
public class PromotionDaoSqls {
	public static final String SELECT_ALL = 
		"SELECT " + 
		"  promotion.id AS id, " + 
		"  promotion.product_id AS productId, " + 
		"  file_info.save_file_name AS productImageUrl " + 
		"FROM promotion, " + 
		"     product_image, " + 
		"     file_info " + 
		"WHERE promotion.product_id = product_image.product_id " + 
		"AND product_image.type = 'th' " + 
		"AND product_image.file_id = file_info.id " +
		"ORDER BY promotion.id ASC";
}
