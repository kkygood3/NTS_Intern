/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.queries;

public class ProductImageQueries {
	public static final String SELECT_PRODUCT_IMAGE = 
		"SELECT product_image.product_id, product_image.id AS product_image_id, product_image.type, product_image.file_id as file_info_id, file_info.file_name, file_info.save_file_name, file_info.content_type, file_info.delete_flag, file_info.create_date, file_info.modify_date"
		+ " FROM product_image"
		+ " INNER JOIN product ON product_image.product_id = product.id"
		+ " INNER JOIN file_info ON product_image.file_id = file_info.id"
		+ " WHERE product_image.type = 'ma' AND product.id = :productId";
}
