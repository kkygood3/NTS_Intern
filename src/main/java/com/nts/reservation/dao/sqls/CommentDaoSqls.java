package com.nts.reservation.dao.sqls;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public class CommentDaoSqls {
	public static final String SELECT_COMMENT_IMAGE_BY_IMAGE_ID = "SELECT fi.id as fileId"
		+ ", ruci.id as imageId"
		+ ", ruci.reservation_info_id as reservationInfoId"
		+ ", fi.file_name as fileName"
		+ ", fi.save_file_name as saveFileName"
		+ ", fi.content_type as contentType"
		+ ", fi.delete_flag as deleteFlag"
		+ ", fi.create_date as createDate"
		+ ", fi.modify_date as modifyDate"
		+ " FROM file_info fi "
		+ " INNER JOIN reservation_user_comment_image ruci ON ruci.file_id = fi.id"
		+ " AND ruci.id = :commentImageId";

	public static final String SELECT_DETAIL_COMMENTS = "SELECT ruc.id AS commentId "
		+ ", di.product_id AS productId "
		+ ", ri.id AS reservationInfoId"
		+ ", IFNULL(CONVERT( ruc.score, DECIMAL(2,1)), 0 ) AS score"
		+ ", ruc.comment AS comment "
		+ ", ri.reservation_name AS reservationName"
		+ ", CONCAT(SUBSTRING(ri.reservation_email , 1, 4), '****') AS reservationEmail"
		+ ", ri.reservation_date AS reservationDate"
		+ ", ri.create_date AS createDate"
		+ ", ri.modify_date AS modifyDate"
		+ " FROM reservation_user_comment ruc"
		+ " INNER JOIN display_info di ON di.product_id = ruc.product_id AND di.id = :display_info_id"
		+ " INNER JOIN reservation_info ri ON ri.id = ruc.reservation_info_id"
		+ " ORDER BY ruc.id DESC";

	public static final String SELECT_DETAIL_COMMENTS_IMAGES_BY_COMMENT_ID = "SELECT ruci.id as imageId"
		+ ", ruc.reservation_info_id as reservationInfoId"
		+ ", fi.id as fileId"
		+ ", fi.file_name as fileName"
		+ ", fi.save_file_name as saveFileName"
		+ ", fi.content_type as contentType"
		+ ", fi.delete_flag as deleteFlag"
		+ ", fi.create_date as createDate"
		+ ", fi.modify_date as modifyDate"
		+ " FROM reservation_user_comment ruc"
		+ " INNER JOIN display_info di ON di.product_id = ruc.product_id"
		+ " INNER JOIN reservation_user_comment_image ruci ON ruc.id = ruci.reservation_user_comment_id "
		+ " AND ruci.reservation_user_comment_id = :commentId"
		+ " INNER JOIN file_info fi ON ruci.file_id = fi.id"
		+ " ORDER BY ruc.id DESC";
}
