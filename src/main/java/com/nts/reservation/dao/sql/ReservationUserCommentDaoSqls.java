package com.nts.reservation.dao.sql;

public class ReservationUserCommentDaoSqls {
	public static final String SELECT_AVERAGE_SCORE_BY_ID = "SELECT round(avg(score), 1) AS averageScore FROM reservation_user_comment WHERE product_id = :productId";
	public static final String SELECT_COUNT_BY_ID = "SELECT count(score) AS averageScore FROM reservation_user_comment WHERE product_id = :productId";
}
