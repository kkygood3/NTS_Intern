/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class DetailCommentSqls {
	public static String SELECT_DETAIL_COMMENT =
		"SELECT reservation_info.display_info_id, DATE_FORMAT(reservation_info.reservation_date,'%Y.%c.%d') AS reservation_date, reservation_user_comment.comment, reservation_user_comment.score, reservation_info.reservation_name, file_info.save_file_name as comment_image" + 
		" FROM reservation_info" + 
		" LEFT JOIN reservation_user_comment ON reservation_info.id = reservation_user_comment.reservation_info_id" + 
		" LEFT JOIN reservation_user_comment_image ON reservation_info.id = reservation_user_comment_image.reservation_info_id" + 
		" LEFT JOIN file_info ON reservation_user_comment_image.file_id = file_info.id" + 
		" WHERE reservation_info.display_info_id = :displayInfoId" + 
		" ORDER BY reservation_user_comment.id DESC" + 
		" LIMIT :pagingLimit"; 
}
