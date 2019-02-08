/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.queries;

public class DisplayInfoImageQueries {
	public static final String SELECT_DISPLAY_INFO_IMAGE = 
		"SELECT file_info.content_type, file_info.create_date, file_info.delete_flag, display_info_image.display_info_id AS display_info_id, display_info_image.id AS display_info_image_id, display_info_image.file_id AS file_id, file_info.file_name, file_info.modify_date, file_info.save_file_name"
		+ " FROM display_info_image"
		+ " LEFT JOIN file_info ON display_info_image.file_id = file_info.id"
		+ " WHERE display_info_image.display_info_id = :displayInfoId";
}
