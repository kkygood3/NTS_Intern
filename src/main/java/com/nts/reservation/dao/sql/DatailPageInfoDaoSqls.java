package com.nts.reservation.dao.sql;

public class DatailPageInfoDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID
	= "SELECT p.id AS product_id, p.description, p.content, d.place_name, d.place_street, d.place_lot, d.tel, pf.save_file_name AS main_image_file, count(uc.score) AS comment_count, ifnull(avg(uc.score), 0) AS average_score, df.save_file_name AS map_file "
	+ "FROM display_info AS d "
	+ "INNER JOIN product AS p ON d.id = :displayInfoId AND d.product_id = p.id "
	+ "INNER JOIN display_info_image AS di ON di.display_info_id = d.id "
	+ "INNER JOIN file_info AS df ON di.file_id = df.id "
	+ "LEFT JOIN reservation_user_comment AS uc ON uc.product_id = p.id "
	+ "INNER JOIN product_image AS pi ON pi.product_id = p.id AND type='ma' "
	+ "INNER JOIN file_info AS pf ON pf.id = pi.file_id";
}
