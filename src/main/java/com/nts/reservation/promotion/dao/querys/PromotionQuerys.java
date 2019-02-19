/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.promotion.dao.querys;

public class PromotionQuerys {

	public static final String SELECT_PROMOTION_LIST = "select p.id as id"
		+ ", p.product_id as product_id"
		+ ", f.save_file_name as product_image_url "
		+ "from promotion p "
		+ "inner join product_image i "
		+ "on p.product_id = i.product_id "
		+ "inner join file_info f "
		+ "on i.file_id = f.id "
		+ "where i.type='th'";

}
