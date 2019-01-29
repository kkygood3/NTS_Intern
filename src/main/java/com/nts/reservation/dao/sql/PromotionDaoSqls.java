package com.nts.reservation.dao.sql;

public class PromotionDaoSqls {
	public static final String SELECT_SAVE_FILE_NAME = 
		"SELECT save_file_name FROM file_info WHERE id IN ("
		+ "	SELECT file_id FROM product_image WHERE type = 'ma' AND product_id IN ("
		+ "		SELECT id FROM product  WHERE id IN ("
		+ "			SELECT product_id FROM promotion)));";
}
