package com.nts.reservation.dao.sql;

public class ProductDaoSqls {
	public static final String SELECT_ALL_PAGING = "SELECT p.id, p.description, di.place_name, p.content "
		+ "FROM (SELECT id, description, content FROM product) as p "
		+ "JOIN (SELECT product_id, place_name FROM display_info) as di "
		+ "WHERE p.id = di.product_id "
		+ "ORDER BY id DESC limit :start, :limit";
	public static final String SELECT_BY_CATERORY_PAGING = "SELECT p.id, p.description, di.place_name, p.content "
		+ "FROM (SELECT id, category_id, description, content FROM product) as p "
		+ "JOIN (SELECT product_id, place_name FROM display_info) as di "
		+ "WHERE p.category_id = :category_id AND p.id = di.product_id "
		+ "ORDER BY id DESC limit :start, :limit";
	public static final String SELECT_COUNT = "SELECT count(id) FROM product";
}
