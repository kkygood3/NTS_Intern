package com.nts.reservation.dao;

public class ProductDaoSqls {
	public static final String SELECT_PROMOTION = "SELECT * FROM promotion JOIN product WHERE product.id = promotion.product_id";
	public static final String SELECT_PAGING = "SELECT * FROM product WHERE category_id=:categoryId ORDER BY id DESC limit :start, :limit";
	public static final String SELECT_BY_ID = "SELECT * FROM product WHERE id = :id";
}
