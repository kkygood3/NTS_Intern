/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class DetailSqls {
	public static final String SELECT_DETAIL_DISPLAY_INFO = 
		"SELECT display_info.id AS display_info_id, product.description AS product_description, product.content AS product_content, display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel AS telephone, product_file_info.save_file_name AS product_image, IFNULL(ROUND(AVG(reservation_user_comment.score),1),0) AS average_score, COUNT(reservation_user_comment.id) as comment_count, display_file_info.save_file_name AS display_info_image" + 
		" FROM display_info" + 
		" INNER JOIN product ON display_info.product_id = product.id" + 
		" INNER JOIN product_image ON product.id = product_image.product_id AND product_image.type = 'th'" + 
		" LEFT JOIN reservation_user_comment ON product.id = reservation_user_comment.product_id" + 
		" INNER JOIN display_info_image ON display_info.id = display_info_image.display_info_id" + 
		" INNER JOIN file_info AS product_file_info ON product_image.file_id = product_file_info.id" + 
		" INNER JOIN file_info AS display_file_info ON display_info_image.file_id = display_file_info.id" + 
		" WHERE display_info.id = :displayInfoId";
	
	public static final String SELECT_EXTRA_IMAGE =
		"SELECT display_info.id, file_info.save_file_name AS product_image, product.description as product_description FROM product_image" + 
		" INNER JOIN file_info ON file_info.id = product_image.file_id" + 
		" INNER JOIN display_info ON product_image.product_id = display_info.product_id" + 
		" INNER JOIN product ON display_info.product_id = product.id" + 
		" WHERE type = 'et' AND display_info.id = :displayInfoId" + 
		" GROUP BY display_info.id";
}
