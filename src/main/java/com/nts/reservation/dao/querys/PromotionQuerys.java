/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.querys;

public class PromotionQuerys {

	public static final String SELECT_PROMOTION_LIST = "select p.id as id, "
		+ "p.product_id as product_id, f.save_file_name as product_image_url "
		+ "from promotion p, product_image i, file_info f "
		+ "where p.product_id = i.product_id "
		+ "and i.file_id = f.id and i.type='th';";

}
