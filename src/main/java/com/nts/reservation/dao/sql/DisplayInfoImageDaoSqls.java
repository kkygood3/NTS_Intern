/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class DisplayInfoImageDaoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_ID = "SELECT file.content_type,"
		+ " file.create_date,"
		+ " file.delete_flag,"
		+ " file.modify_date,"
		+ " file.save_file_name,"
		+ " file.file_name,"
		+ " file.id AS file_id,"
		+ " image.display_info_id,"
		+ " image.id AS display_info_image_id"
		+ " FROM  display_info_image AS image,"
		+ " file_info AS file"
		+ " WHERE image.file_id = file.id"
		+ " AND image.display_info_id = :id";
}
