/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.queries.detail;

public class DetailExtraImageQueries {
	public static final String SELECT_EXTRA_IMAGE =
		"SELECT display_info.id, file_info.save_file_name AS product_image, product.description as product_description FROM product_image" + 
		" INNER JOIN file_info ON file_info.id = product_image.file_id" + 
		" INNER JOIN display_info ON product_image.product_id = display_info.product_id" + 
		" INNER JOIN product ON display_info.product_id = product.id" + 
		" WHERE type = 'et' AND display_info.id = :displayInfoId" + 
		" GROUP BY display_info.id";
}
