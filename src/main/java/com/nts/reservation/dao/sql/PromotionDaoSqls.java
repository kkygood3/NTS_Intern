package com.nts.reservation.dao.sql;

public class PromotionDaoSqls {
	public static final String SELECT_MA_TYPE_SAVE_FILE_NAMES_IN_PROMOTION = 
		"SELECT save_file_name FROM file_info WHERE id IN ("
		+ "	SELECT file_id FROM product_image WHERE type = 'ma' AND product_id IN ("
		+ "		SELECT id FROM product  WHERE id IN ("
		+ "			SELECT product_id FROM promotion)))";
}
