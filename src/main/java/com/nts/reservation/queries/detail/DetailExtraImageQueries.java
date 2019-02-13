package com.nts.reservation.queries.detail;

public class DetailExtraImageQueries {
	public static final String SELECT_EXTRA_IMAGE =
		"SELECT display_info.id, file_info.save_file_name AS product_image FROM user4.product_image\r\n"
		+ " INNER JOIN file_info ON file_info.id = product_image.file_id\r\n"
		+ " INNER JOIN display_info ON product_image.product_id = display_info.product_id\r\n"
		+ " WHERE type = 'et' AND display_info.id = :displayInfoId"
		+ " GROUP BY display_info.id";
}
