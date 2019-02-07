package com.nts.reservation.dao.sql;

public class PromotionDaoSqls {
	public static final String SELECT_MA_TYPE_SAVE_FILE_NAMES_IN_PROMOTION =  "SELECT save_file_name "
		+ "FROM file_info INNER JOIN ( "
		+ "	SELECT product_image.file_id "
		+ "	FROM product_image INNER JOIN ( "
		+ "		SELECT product.id "
		+ "        FROM product INNER JOIN promotion "
		+ "        WHERE promotion.product_id = product.id) AS p "
		+ "	WHERE type = 'ma' AND p.id = product_image.product_id) AS pi "
		+ "WHERE file_info.id = pi.file_id;";
}
