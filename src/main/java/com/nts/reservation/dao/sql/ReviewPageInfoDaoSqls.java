package com.nts.reservation.dao.sql;

public class ReviewPageInfoDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID = "SELECT p.product_id, p.description, ifnull(avg(ruc.score), 0) AS average_score, count(ruc.score) AS commentCount "
			+ "FROM ( "
			+ "	SELECT product.id AS product_id, product.description " 
			+ "    FROM product INNER JOIN display_info ON product.id = display_info.product_id " 
			+ "    WHERE product.id = (SELECT product_id FROM display_info WHERE id = :displayInfoId) " 
			+ ") AS p " 
			+ "LEFT JOIN reservation_user_comment AS ruc " 
			+ "ON ruc.product_id = p.product_id;";
}
