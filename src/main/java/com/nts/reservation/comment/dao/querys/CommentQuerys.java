/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao.querys;

public class CommentQuerys {

	public static final String SELECT_PRODUCT_DISPLAY_COMMENT_LIST_ALL = "select rc.id as comment_id"
		+ ", p.description as product_description"
		+ ", ri.reservation_date as reservation_date"
		+ ", substr(ri.reservation_email, 1, 4) as reservation_email"
		+ ", rc.comment as comment"
		+ ", rc.score as score"
		+ ", ci.id as comment_image_id "
		+ "from reservation_info ri "
		+ "inner join product p "
		+ "on p.id = ri.product_id "
		+ "inner join display_info d "
		+ "on d.product_id = p.id "
		+ "inner join reservation_user_comment rc "
		+ "on ri.id = rc.reservation_info_id "
		+ "left outer join reservation_user_comment_image ci "
		+ "on rc.id = ci.reservation_user_comment_id "
		+ "left outer join file_info fi "
		+ "on ci.file_id = fi.id "
		+ "where d.id = :displayInfoId";

	public static final String SELECT_PRODUCT_DISPLAY_COMMENT_LIST_LIMIT = "select rc.id as comment_id"
		+ ", p.description as product_description"
		+ ", ri.reservation_date as reservation_date"
		+ ", substr(ri.reservation_email, 1, 4) as reservation_email"
		+ ", rc.comment as comment"
		+ ", rc.score as score"
		+ ", ci.id as comment_image_id "
		+ "from reservation_info ri "
		+ "inner join product p "
		+ "on p.id = ri.product_id "
		+ "inner join display_info d "
		+ "on d.product_id = p.id "
		+ "inner join ("
		+ "select rc.id, rc.reservation_info_id, rc.comment, rc.score "
		+ "from reservation_user_comment rc "
		+ "inner join display_info d "
		+ "on rc.product_id = d.product_id "
		+ "where d.id = :displayInfoId "
		+ "limit :limitCount"
		+ ") as rc "
		+ "on ri.id = rc.reservation_info_id "
		+ "left outer join reservation_user_comment_image ci "
		+ "on rc.id = ci.reservation_user_comment_id "
		+ "left outer join file_info fi "
		+ "on ci.file_id = fi.id "
		+ "where d.id = :displayInfoId";

	public static final String SELECT_PRODUCT_DISPLAY_COMMENT_LIST_INFO = "select coalesce(avg(score), 0) as average_score"
		+ ", count(*) as total_count "
		+ "from reservation_user_comment rc "
		+ "inner join product p "
		+ "on rc.product_id = p.id "
		+ "inner join display_info d "
		+ "on d.product_id = p.id "
		+ "where d.id = :displayInfoId;";

	public static final String INSERT_RESERVATION_USER_COMMENT = "insert into "
		+ "reservation_user_comment("
		+ "product_id"
		+ ", reservation_info_id"
		+ ", score"
		+ ", comment"
		+ ", create_date"
		+ ", modify_date"
		+ ") "
		+ "select "
		+ "ri.product_id as product_id"
		+ ", :reservationId as reservation_info_id"
		+ ", :score as score"
		+ ", :comment as comment"
		+ ", now() as create_date"
		+ ", now() as modify_date "
		+ "from reservation_info ri "
		+ "where ri.id = :reservationId "
		+ "and ri.reservation_email = :reservationEmail";

	public static final String INSERT_RESERVATION_USER_COMMENT_IMAGE_INFO = "insert into "
		+ "reservation_user_comment_image values("
		+ "default"
		+ ", :reservationId"
		+ ", :reservationUserCommentId"
		+ ", :fileId"
		+ ")";

	public static final String SELECT_COMMENT_SAVE_FILE_NAME = "select fi.file_name as file_name"
		+ ", fi.save_file_name as save_file_name"
		+ ", fi.content_type as content_type "
		+ "from reservation_user_comment_image ci "
		+ "inner join file_info fi "
		+ "on ci.file_id = fi.id "
		+ "where ci.id = :commentImageId";
}
