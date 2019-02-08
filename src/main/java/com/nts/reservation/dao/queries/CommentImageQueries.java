/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.queries;

public class CommentImageQueries {
	public static final String SELECT_COMMENT_IMAGE = 
		"SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, file_info.id as file_id, file_info.file_name, reservation_user_comment_image.id as image_id, file_info.modify_date, reservation_user_comment_image.reservation_info_id, reservation_user_comment_image.reservation_user_comment_id, file_info.save_file_name"
		+ " FROM reservation_user_comment_image"
		+ " INNER JOIN file_info on reservation_user_comment_image.file_id = file_info.id";
}
