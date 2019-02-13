/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CommentDaoQuerys {
	public static final String SELECT_COMMENT_BY_PRODUCT = "SELECT reservation_user_comment.id, reservation_user_comment.score, product.description, reservation_user_comment.comment, reservation_user_comment.score, reservation_info.reservation_name, reservation_info.reservation_date"
		+ "FROM reservation_user_comment"
		+ "INNER JOIN product ON reservation_user_comment.id = product.id"
		+ "INNER JOIN reservation_info ON reservation_user_comment.id = reservation_info.id"
		+ "WHERE reservation_user_comment.product_id = :productId";

	public static final String SELECT_AVERAGE_SCORE_BY_PRODUCT = "SELECT ( SUM( score ) / count( reservation_user_comment.id ) )"
		+ "FROM reservation_user_comment"
		+ "WHERE reservation_user_comment.product_id = :productId";
}
