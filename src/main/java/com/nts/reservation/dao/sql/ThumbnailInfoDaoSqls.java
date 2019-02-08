package com.nts.reservation.dao.sql;

public class ThumbnailInfoDaoSqls {
	public static final String SELECT_ALL_WITH_PAGING = "SELECT pi.product_id, pi.display_info_id, pi.description, pi.place_name, pi.content, fi.save_file_name " +
		"FROM ( " +
		"	SELECT p.id AS product_id, p.description, di.display_info_id, di.place_name, p.content, p.category_id " +
		"    FROM (SELECT id, description, content, category_id FROM product) AS p " +
		"	JOIN (SELECT id AS display_info_id, product_id, place_name FROM display_info) AS di " +
		"    WHERE p.id = di.product_id) AS pi " +
		"JOIN ( " +
		"	SELECT file_info.save_file_name, product_image.product_id " +
		"	FROM file_info " +
		"    JOIN product_image " +
		"    WHERE file_info.id = product_image.file_id AND product_image.type = 'th') AS fi "
		+ "WHERE pi.product_id = fi.product_id "
		+ "ORDER BY pi.product_id DESC limit :start, :limit";

	public static final String SELECT_BY_CATERORY_WITH_PAGING = "SELECT pi.product_id, pi.display_info_id, pi.description, pi.place_name, pi.content, fi.save_file_name " +
		"FROM ( " +
		"	SELECT p.id AS product_id, p.description, di.display_info_id, di.place_name, p.content, p.category_id " +
		"    FROM (SELECT id, description, content, category_id FROM product) AS p " +
		"	JOIN (SELECT id AS display_info_id, product_id, place_name FROM display_info) AS di " +
		"    WHERE p.id = di.product_id) AS pi " +
		"JOIN ( " +
		"	SELECT file_info.save_file_name, product_image.product_id " +
		"	FROM file_info " +
		"    JOIN product_image " +
		"    WHERE file_info.id = product_image.file_id AND product_image.type = 'th') AS fi "
		+ "WHERE pi.product_id = fi.product_id AND pi.category_id = :categoryId "
		+ "ORDER BY pi.product_id DESC limit :start, :limit";
}
