/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class ReviewSqls {
	public static final String SELECT_REVIEW_DISPLAY_INFO = 
		"SELECT display_info.id AS display_info_id, product.description AS product_description, IFNULL(ROUND(AVG(reservation_user_comment.score),1),0) AS average_score, COUNT(reservation_user_comment.id) as comment_count" + 
		" FROM display_info" + 
		" INNER JOIN product ON display_info.product_id = product.id" + 
		" INNER JOIN reservation_user_comment ON product.id = reservation_user_comment.product_id" + 
		" WHERE display_info.id = :displayInfoId";
	
	public static final String SELECT_REVIEW_COMMENT = com.nts.reservation.dao.sql.common.CommonSqls.SELECT_COMMENT;
}
