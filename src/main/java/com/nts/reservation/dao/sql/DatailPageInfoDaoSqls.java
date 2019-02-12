package com.nts.reservation.dao.sql;

public class DatailPageInfoDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID = "SELECT * "
		+ "FROM ( "
		+ "	SELECT p.product_id, p.description, p.content, p.save_file_name AS main_image_file, count(u_comment.score) AS comment_count, nvl(avg(u_comment.score), 0) AS average_score "
		+ "	FROM ( "
		+ "		SELECT pi.product_id, pi.description, pi.content, file_info.save_file_name "
		+ "		FROM ( "
		+ "			SELECT product.id AS product_id, product.description, product.content, product_image.file_id "
		+ "           FROM product INNER JOIN product_image on product.id = product_image.product_id "
		+ "           WHERE product.id = (SELECT display_info.product_id FROM display_info WHERE id = :displayInfoId) AND product_image.type = 'ma' "
		+ "		) AS pi "
		+ "		INNER JOIN file_info on pi.file_id = file_info.id "
		+ "	) AS p "
		+ "	LEFT JOIN reservation_user_comment AS u_comment "
		+ "	ON p.product_id = u_comment.product_id "
		+ ") AS d "
		+ "INNER JOIN ( "
		+ "	SELECT di.place_name, di.place_street, di.place_lot, di.tel, file_info.save_file_name AS map_file "
		+ "    FROM ( "
		+ "		SELECT display_info.place_name, display_info.place_street, display_info.place_lot, display_info.tel, display_info_image.file_id "
		+ "        FROM display_info INNER JOIN display_info_image ON display_info.id = display_info_image.display_info_id "
		+ "        WHERE display_info.id = :displayInfoId "
		+ "	) AS di "
		+ "    INNER JOIN file_info ON di.file_id = file_info.id "
		+ ") AS p;";
}
