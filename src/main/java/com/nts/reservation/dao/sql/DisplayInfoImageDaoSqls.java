/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class DisplayInfoImageDaoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_ID = "SELECT file_info.content_type,"
		+ " file_info.create_date,"
		+ " file_info.delete_flag,"
		+ " file_info.modify_date,"
		+ " file_info.save_file_name,"
		+ " file_info.file_name,"
		+ " file_info.id AS file_id,"
		+ " image.display_info_id,"
		+ " image.id AS display_info_image_id"
		+ " FROM  display_info_image AS image,"
		+ " file_info"
		+ " WHERE image.file_id = file_info.id"
		+ " AND image.display_info_id = :id";
}
