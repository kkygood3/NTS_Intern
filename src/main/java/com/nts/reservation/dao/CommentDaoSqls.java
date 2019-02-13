/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

/**
 * @author jinwoo.bae
 *
 */
public class CommentDaoSqls {
	public static final String SELECT_COMMENTS = ""
		+ "SELECT  "
		+ "    ruc.id AS id, "
		+ "    ruc.score AS score, "
		+ "    ruc.comment AS comment, "
		+ "    ri.reservation_name AS reservationName, "
		+ "    ri.reservation_date AS reservationDate, "
		+ "    ri.modify_date AS modifyDate, "
		+ "    fi.save_file_name AS saveFileName "
		+ "FROM "
		+ "    reservation_info ri "
		+ "        INNER JOIN "
		+ "    reservation_user_comment ruc ON ri.id = ruc.reservation_info_id "
		+ "        LEFT JOIN "
		+ "    reservation_user_comment_image ruci ON ruc.id = ruci.reservation_user_comment_id "
		+ "        LEFT JOIN "
		+ "    file_info fi ON fi.id = ruci.file_id "
		+ "WHERE "
		+ "    ri.product_id = :productId "
		+ "GROUP BY ruc.id "
		+ "ORDER BY ruc.id DESC "
		+ "LIMIT :start , :limit;";

	public static final String SELECT_COMMENT_AVG_SCORE = ""
		+ "SELECT  "
		+ "    ROUND(IFNULL(AVG(score), 0), 1) "
		+ "FROM "
		+ "    reservation_user_comment "
		+ "WHERE "
		+ "    product_id = :productId";

	public static final String SELECT_COMMENT_COUNT = ""
		+ "SELECT  "
		+ "    COUNT(id) "
		+ "FROM "
		+ "    reservation_user_comment "
		+ "WHERE "
		+ "    product_id = :productId";
}
