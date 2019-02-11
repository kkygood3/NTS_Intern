/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.queries.detail;

public class CommentQueries {
	public static final String SELECT_COMMENT = 
		"SELECT reservation_user_comment.comment, reservation_user_comment.id as commentId, reservation_user_comment.create_date, reservation_user_comment.modify_date, reservation_user_comment.product_id, reservation_info.reservation_date, reservation_info.reservation_email, reservation_user_comment.reservation_info_id, reservation_info.reservation_name, reservation_info.reservation_tel as reservation_telephone, reservation_user_comment.score"
		+ " FROM reservation_user_comment"
		+ " INNER JOIN reservation_info ON reservation_user_comment.reservation_info_id = reservation_info.id"
		+ " WHERE reservation_user_comment.product_id = :productId"
		+ " ORDER BY reservation_user_comment.id DESC";
	
	public static final String SELECT_AVG_SCORE =
		"SELECT AVG(reservation_user_comment.score)"
		+ " FROM reservation_user_comment"
		+ " WHERE reservation_user_comment.product_id = :productId";
}
