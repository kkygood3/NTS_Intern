package com.nts.reservation.dao.sql;

public class ReviewPageInfoDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID
	= "SELECT p.id AS product_id, p.description, ifnull(avg(uc.score), 0) AS average_score, count(uc.score) AS commentCount "
	+ "FROM product AS p "
	+ "INNER JOIN display_info AS d ON d.id = :displayInfoId AND d.product_id = p.id "
	+ "INNER JOIN reservation_user_comment AS uc ON d.id = uc.product_id";
}
