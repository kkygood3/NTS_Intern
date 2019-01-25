/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.layer.promotion.sqls;

public class PromotionSqls {
	public static final String SELECT_PROMOTIONS =
		"SELECT prom.id AS id,prom.product_id AS product_id,file_info.save_file_name AS product_image_url " + 
		"FROM file_info " + 
		"RIGHT JOIN (SELECT pr_image.file_id, pr_image.product_id, prm.id " + 
		"FROM promotion AS prm " + 
		"LEFT JOIN  " + 
		"(SELECT product_id, file_id " + 
		"FROM product_image  " + 
		"WHERE product_image.type = 'th') AS pr_image " + 
		"ON prm.product_id = pr_image.product_id) AS prom " + 
		"ON file_info.id = prom.file_id " + 
		"WHERE delete_flag = 0";
}
