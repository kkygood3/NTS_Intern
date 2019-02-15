package com.nts.reservation.dao.sql;

public class CommentDisplayInfoDaoSqls {
	public static final String SELECT_COMMENT_WITH_PAGING
	= "SELECT uc.comment, uc.score, concat(left(ri.reservation_email, 4), '****') AS reservation_email, ri.reservation_date, ifnull(fi.save_file_name, '') AS save_file_name "
	+ "FROM reservation_info AS ri "
	+ "INNER JOIN reservation_user_comment AS uc ON ri.id = uc.reservation_info_id AND ri.product_id = :productId AND uc.product_id = :productId "
	+ "LEFT JOIN reservation_user_comment_image AS uci ON uci.reservation_user_comment_id = uc.id "
	+ "LEFT JOIN file_info AS fi ON fi.id = uci.file_id "
	+ "LIMIT :start, :limit";
}
