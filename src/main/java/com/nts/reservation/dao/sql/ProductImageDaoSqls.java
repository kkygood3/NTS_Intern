/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGE_BY_ID = "SELECT file_info.content_type,"
		+ " file_info.create_date,"
		+ " file_info.delete_flag,"
		+ " file_info.id AS file_info_id,"
		+ " file_info.file_name,"
		+ " file_info.modify_date,"
		+ " image.product_id,"
		+ " image.id AS product_image_id,"
		+ " file_info.save_file_name,"
		+ " image.type FROM file_info,"
		+ " product_image AS image"
		+ " WHERE image.file_id = file_info.id"
		+ " AND image.type != \"th\""
		+ " AND image.product_id = :id";
}
