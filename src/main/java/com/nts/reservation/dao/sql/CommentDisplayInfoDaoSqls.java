package com.nts.reservation.dao.sql;

public class CommentDisplayInfoDaoSqls {
	public static final String SELECT_COMMENT_WITH_PAGING = "SELECT concat(substr(ri.reservation_email, 1, 4), '****') AS reservation_email, ri.reservation_date, ruc.score, ruc.comment, fi.save_file_name "
		+ "FROM reservation_info AS ri "
		+ "	INNER JOIN reservation_user_comment AS ruc "
		+ "		ON ri.id = ruc.reservation_info_id "
		+ "	LEFT JOIN (SELECT ruci.reservation_info_id, fi.save_file_name FROM reservation_user_comment_image AS ruci INNER JOIN file_info AS fi ON ruci.file_id = fi.id) AS fi "
		+ "		ON fi.reservation_info_id = ri.id "
		+ "WHERE ri.product_id = :productId "
		+ "LIMIT :start, :limit";
}
