/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class ProductImageDaoSqls {
	public static final String SELECT_PRODUCT_IMAGE_BY_ID = "SELECT file.content_type,"
		+ " file.create_date,"
		+ " file.delete_flag,"
		+ " file.id AS file_info_id,"
		+ " file.file_name,"
		+ " file.modify_date,"
		+ " image.product_id,"
		+ " image.id AS product_image_id,"
		+ " file.save_file_name,"
		+ " image.type FROM file_info AS file,"
		+ " product_image AS image"
		+ " WHERE image.file_id = file.id"
		+ " AND image.type != \"th\""
		+ " AND image.product_id = :id";
}
