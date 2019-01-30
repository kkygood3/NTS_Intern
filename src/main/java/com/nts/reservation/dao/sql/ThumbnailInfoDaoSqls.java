package com.nts.reservation.dao.sql;

public class ThumbnailInfoDaoSqls {
	public static final String SELECT_ALL_PAGING = "SELECT pi.id AS product_id, pi.description, pi.place_name, pi.content, fi.save_file_name "
		+ "FROM (SELECT p.id, p.description, di.place_name, p.content "
		+ "FROM (SELECT id, description, content FROM product) AS p "
		+ "JOIN (SELECT product_id, place_name FROM display_info) AS di "
		+ "WHERE p.id = di.product_id) AS pi "
		+ "JOIN (SELECT file_info.save_file_name, product_image.product_id "
		+ "FROM file_info JOIN product_image "
		+ "WHERE file_info.id = product_image.file_id AND product_image.type = 'th') AS fi "
		+ "WHERE pi.id = fi.product_id "
		+ "ORDER BY id DESC limit :start, :limit";

	public static final String SELECT_BY_CATERORY_PAGING = "SELECT p.id AS id, p.description AS description, di.place_name AS place_name, p.content AS content "
		+ "FROM (SELECT id, category_id, description, content FROM product) as p "
		+ "JOIN (SELECT product_id, place_name FROM display_info) as di "
		+ "WHERE p.category_id = :category_id AND p.id = di.product_id "
		+ "ORDER BY id DESC limit :start, :limit";
}
