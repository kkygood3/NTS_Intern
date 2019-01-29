/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.sqls.promotion;

/**
 * @author 전연빈
 */
public class PromotionSqls {
	public static final String SELECT_PROMOTIONS =
		"SELECT pr.id AS id,pr.product_id AS product_id, f.save_file_name AS product_image_url " + 
		"FROM promotion pr INNER JOIN product_image p_img INNER JOIN file_info f " + 
		"ON pr.id = p_img.product_id AND p_img.file_id = f.id " + 
		"WHERE p_img.type ='th' AND f.delete_flag = 0";
}
