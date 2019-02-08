/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.resevation.dao;

/**
 * @author jinwoo.bae
 *
 */
public class CommentDaoSqls {
	public static final String SELECT_COMMENTS = ""
		+ "SELECT  "
		+ "    c.id AS commentId, "
		+ "    c.product_id AS productId, "
		+ "    ri.id AS reservationInfoId, "
		+ "    c.score AS score, "
		+ "    c.comment AS comment, "
		+ "    ri.reservation_name AS reservationName, "
		+ "    ri.reservation_tel AS reservationTelephone, "
		+ "    ri.reservation_email AS reservationEmail, "
		+ "    ri.reservation_date AS reservationDate, "
		+ "    ri.create_date AS createDate, "
		+ "    ri.modify_date AS modifyDate "
		+ "FROM "
		+ "    reservation_user_comment c, "
		+ "    reservation_info ri "
		+ "WHERE "
		+ "    c.reservation_info_id = ri.id "
		+ "        AND c.product_id = :productId "
		+ "ORDER BY c.id DESC "
		+ "LIMIT :start , :limit";

	public static final String SELECT_COMMENT_IMAGE = ""
		+ "SELECT  "
		+ "    ci.id AS imageId, "
		+ "    ci.reservation_info_id AS reservationInfoId, "
		+ "    ci.reservation_user_comment_id AS reservationUserCommentId, "
		+ "    fi.id AS fileId, "
		+ "    fi.file_name AS filename, "
		+ "    fi.save_file_name AS saveFileName, "
		+ "    fi.content_type AS contentType, "
		+ "    fi.delete_flag As deleteFlag, "
		+ "    fi.create_date AS createDate, "
		+ "    fi.modify_date AS modifyDate "
		+ "FROM "
		+ "    reservation_user_comment_image ci, "
		+ "    file_info fi "
		+ "WHERE "
		+ "    ci.file_id = fi.id AND ci.id = :commentId";

	public static final String SELECT_COMMENT_AVG_SCORE = ""
		+ "SELECT  "
		+ "    IFNULL(AVG(score), 0) "
		+ "FROM "
		+ "    reservation_user_comment "
		+ "WHERE "
		+ "    product_id = :productId";
}
