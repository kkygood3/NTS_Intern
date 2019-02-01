/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class CommentImageDaoSqls {
	public static final String SELECT_COMMENT_IMAGE_BY_ID = "SELECT file_info.content_type,"
		+ " file_info.create_date,"
		+ " file_info.delete_flag,"
		+ " file_info.id AS file_id,"
		+ " file_info.file_name,"
		+ " image.id AS image_id,"
		+ " file_info.modify_date,"
		+ " image.reservation_info_id,"
		+ " image.reservation_user_comment_id,"
		+ " file_info.save_file_name"
		+ " FROM reservation_user_comment_image AS image, file_info"
		+ " WHERE file_info.id = image.file_id"
		+ " AND image.reservation_user_comment_id = :id"
		+ " ORDER BY file_info.create_date DESC";
}
