/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class CommentDaoSqls {
	public static final String SELECT_COMMENT_BY_DISPLAY_INFO_ID = "SELECT comment.comment,"
		+ " comment.id AS comment_id,"
		+ " reservation.reservation_date,"
		+ " reservation.reservation_email,"
		+ " comment.reservation_info_id,"
		+ " reservation.reservation_name,"
		+ " reservation.reservation_tel AS reservation_telephone,"
		+ " reservation.product_id,"
		+ " comment.score,"
		+ " reservation.create_date,"
		+ " reservation.modify_date"
		+ " FROM reservation_user_comment AS comment"
		+ " INNER JOIN reservation_info AS reservation"
		+ " ON reservation.id = comment.reservation_info_id"
		+ " WHERE reservation.display_info_id = :id";

	public static final String SELECT_COMMENT_AVG_SCORE_BY_DISPLAY_INFO_ID = "SELECT AVG(comment.score)"
		+ " FROM reservation_user_comment AS comment"
		+ " INNER JOIN reservation_info AS reservation"
		+ " ON reservation.id = comment.reservation_info_id"
		+ " WHERE reservation.display_info_id = :id";
}
