package com.nts.reservation.dao.sql;

public class ProductDaoSqls {
	public static final String SELECT_BY_ID = "SELECT * FROM product WHERE id = :productId";
	public static final String SELECT_COUNT_ALL = "SELECT count(product.id) "
		+ "FROM product JOIN display_info "
		+ "WHERE product.id = display_info.product_id";
	public static final String SELECT_COUNT_BY_CATEGORY = "SELECT count(product.id) "
		+ "FROM product JOIN display_info "
		+ "WHERE product.id = display_info.product_id AND category_id = :categoryId";
}
