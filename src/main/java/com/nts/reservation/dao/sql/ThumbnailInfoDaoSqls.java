package com.nts.reservation.dao.sql;

public class ThumbnailInfoDaoSqls {
	public static final String SELECT_ALL_WITH_PAGING = "SELECT d.product_id, d.id AS display_info_id, p.description, p.content, d.place_name, pf.save_file_name " + 
			"FROM product AS p " + 
			"INNER JOIN product_image AS pi ON pi.type = 'th' AND pi.product_id = p.id " + 
			"INNER JOIN file_info AS pf ON pf.id = pi.file_id " + 
			"INNER JOIN display_info AS d ON d.product_id = p.id "
		+ "ORDER BY pi.product_id DESC limit :start, :limit";

	public static final String SELECT_BY_CATERORY_WITH_PAGING = "SELECT d.product_id, d.id AS display_info_id, p.description, p.content, d.place_name, pf.save_file_name " + 
			"FROM product AS p " + 
			"INNER JOIN category AS c ON c.id = :categoryId AND c.id = p.category_id " + 
			"INNER JOIN product_image AS pi ON pi.type = 'th' AND pi.product_id = p.id " + 
			"INNER JOIN file_info AS pf ON pf.id = pi.file_id " + 
			"INNER JOIN display_info AS d ON d.product_id = p.id "
		+ "ORDER BY pi.product_id DESC limit :start, :limit";
}
