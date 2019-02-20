/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class CommonSqls {
	public static final String SELECT_COMMENT =
		"SELECT" + 
		" rc.id," + 
		" DATE_FORMAT(r.reservation_date,'%Y.%c.%d') AS reservation_date," + 
		" rc.comment," + 
		" rc.score," + 
		" r.reservation_name," + 
		" f.save_file_name as comment_image" + 
		" FROM reservation_info AS r  " + 
		" INNER JOIN reservation_user_comment AS rc ON r.id = rc.reservation_info_id  " + 
		" LEFT JOIN reservation_user_comment_image AS rci ON rc.id = rci.reservation_user_comment_id  " + 
		" LEFT JOIN file_info AS f ON rci.file_id = f.id  " + 
		" WHERE r.display_info_id = :displayInfoId  " + 
		" ORDER BY rc.id DESC  " + 
		" LIMIT :start, :pagingLimit";
}
