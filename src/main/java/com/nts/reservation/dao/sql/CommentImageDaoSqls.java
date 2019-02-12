/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class CommentImageDaoSqls {
	public static final String SELECT_COMMENT_IMAGE_BY_ID = "SELECT file.content_type,"
		+ " file.create_date,"
		+ " file.delete_flag,"
		+ " file.id AS file_id,"
		+ " file.file_name,"
		+ " image.id AS image_id,"
		+ " file.modify_date,"
		+ " image.reservation_info_id,"
		+ " image.reservation_user_comment_id,"
		+ " file.save_file_name"
		+ " FROM reservation_user_comment_image AS image"
		+ " INNER JOIN file_info AS file"
		+ " ON file.id = image.file_id"
		+ " WHERE image.reservation_user_comment_id = :id"
		+ " ORDER BY file.create_date DESC";
}
