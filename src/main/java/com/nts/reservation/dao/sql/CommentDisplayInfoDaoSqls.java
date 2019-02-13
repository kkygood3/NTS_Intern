package com.nts.reservation.dao.sql;

public class CommentDisplayInfoDaoSqls {
	public static final String SELECT_COMMENT_WITH_PAGING = "SELECT ri.comment, ri.score, concat(left(ri.reservation_email, 4), '****') AS reservation_email, ri.reservation_date, fi.save_file_name "
			+ "FROM ( " + "	SELECT ri.comment, ri.score, ri.reservation_email, ri.reservation_date, ruci.file_id "
			+ "	FROM ( "
			+ "		SELECT ri.reservation_email, ri.reservation_date, ruc.comment, ruc.score, ruc.id AS reservation_user_comment_id "
			+ "		FROM ( "
			+ "			SELECT ri.id AS reservation_info_id, ri.reservation_email, ri.reservation_date "
			+ "			FROM reservation_info AS ri "
			+ "			INNER JOIN product AS p ON p.id = ri.product_id "
			+ "			WHERE p.id = :productId "
			+ "		) AS ri "
			+ "		INNER JOIN reservation_user_comment AS ruc ON ruc.reservation_info_id = ri.reservation_info_id "
			+ "	) AS ri "
			+ "	LEFT JOIN reservation_user_comment_image AS ruci ON ri.reservation_user_comment_id = ruci.reservation_user_comment_id "
			+ ") AS ri "
			+ "LEFT JOIN file_info AS fi ON ri.file_id = fi.id "
			+ "LIMIT :start, :limit";
}
