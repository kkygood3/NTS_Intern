/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.querys;

public class CommentQuerys {

	private static final int LIMIT_COUNT = 3;

	public static final String SELECT_PRODUCT_DISPLAY_COMMENT_LIST_ALL = "select p.description as product_description"
		+ ", ri.reservation_date as reservation_date"
		+ ", substr(ri.reservation_email, 1, 4) as reservation_email"
		+ ", rc.comment as comment"
		+ ", rc.score as score"
		+ ", fi.save_file_name as comment_image_url "
		+ "from reservation_info ri "
		+ "inner join product p "
		+ "on p.id = ri.product_id "
		+ "inner join reservation_user_comment rc "
		+ "on ri.id = rc.reservation_info_id "
		+ "left outer join reservation_user_comment_image ci "
		+ "on rc.id = ci.reservation_user_comment_id "
		+ "left outer join file_info fi "
		+ "on ci.file_id = fi.id "
		+ "where ri.display_info_id = :displayInfoId";

	public static final String SELECT_PRODUCT_DISPLAY_COMMENT_LIST_LIMIT = SELECT_PRODUCT_DISPLAY_COMMENT_LIST_ALL
		+ " limit " + LIMIT_COUNT;

	public static final String SELECT_PRODUCT_DISPLAY_COMMENT_META_DATA = "select ifnull(avg(score), 0) as average_score"
		+ ", count(*) as total_count "
		+ "from reservation_user_comment rc "
		+ "inner join product p "
		+ "on rc.product_id = p.id "
		+ "inner join display_info d "
		+ "on d.product_id = p.id "
		+ "where d.id = :displayInfoId;";
}
