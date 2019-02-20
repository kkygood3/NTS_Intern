/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class ReviewSqls {
	public static final String SELECT_REVIEW_DISPLAY_INFO = 
		"SELECT" + 
		" d.id AS display_info_id," + 
		" p.description AS product_description," + 
		" IFNULL(ROUND(AVG(rc.score),1),0) AS average_score," + 
		" COUNT(rc.id) as comment_count" + 
		" FROM display_info AS d" + 
		" INNER JOIN product AS p ON d.product_id = p.id" + 
		" INNER JOIN reservation_user_comment AS rc ON p.id = rc.product_id" + 
		" WHERE d.id = :displayInfoId";
}
