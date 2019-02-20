/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class DetailSqls {
	public static final String SELECT_DETAIL_DISPLAY_INFO = 
		"SELECT" + 
		" d.id AS display_info_id," + 
		" p.description AS product_description," + 
		" p.content AS product_content," + 
		" d.place_name, d.place_lot," + 
		" d.place_street," + 
		" d.tel AS telephone," + 
		" product_file_info.save_file_name AS product_image," + 
		" IFNULL(ROUND(AVG(rc.score),1),0) AS average_score," + 
		" COUNT(rc.id) as comment_count," + 
		" display_file_info.save_file_name AS display_info_image" + 
		" FROM display_info AS d" + 
		" INNER JOIN product AS p ON d.product_id = p.id" + 
		" INNER JOIN product_image AS pi ON p.id = pi.product_id AND pi.type = 'th'" + 
		" LEFT JOIN reservation_user_comment AS rc ON p.id = rc.product_id" + 
		" INNER JOIN display_info_image AS di ON d.id = di.display_info_id" + 
		" INNER JOIN file_info AS product_file_info ON pi.file_id = product_file_info.id" + 
		" INNER JOIN file_info AS display_file_info ON di.file_id = display_file_info.id" +
		" WHERE d.id = :displayInfoId";
	
	public static final String SELECT_EXTRA_IMAGE =
		"SELECT" + 
		" d.id," + 
		" f.save_file_name AS product_image," + 
		" p.description AS product_description" + 
		" FROM product_image AS pi" + 
		" INNER JOIN file_info AS f ON f.id = pi.file_id" + 
		" INNER JOIN display_info AS d ON pi.product_id = d.product_id" + 
		" INNER JOIN product AS p ON d.product_id = p.id" + 
		" WHERE type = 'et' AND d.id = :displayInfoId" + 
		" GROUP BY d.id";
}
