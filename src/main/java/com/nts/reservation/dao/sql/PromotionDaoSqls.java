package com.nts.reservation.dao.sql;

public class PromotionDaoSqls {
	public static final String SELECT_MA_TYPE_SAVE_FILE_NAMES_IN_PROMOTION
	= "SELECT save_file_name AS saveFileName "
	+ "FROM promotion AS prom "
	+ "INNER JOIN product AS prod ON prom.product_id = prod.id "
	+ "INNER JOIN product_image AS prodi ON prodi.type = 'th' AND prodi.product_id = prod.id "
	+ "INNER JOIN file_info AS fi ON fi.id = prodi.file_id";
}
