package com.nts.reservation.dao.sql;

public class FileInfoDaoSqls {
	public static final String SELECT_SAVE_FILE_NAME_BY_DISPLAY_INFO_ID_AND_TYPE = "SELECT file_info.save_file_name "
			+ "FROM product "
			+ "	INNER JOIN product_image ON product.id = product_image.product_id "
			+ "	INNER JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE product_image.type = :type AND product.id = :productId";
}
