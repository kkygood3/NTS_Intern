package com.nts.reservation.dao.sql;

public class PriceInfoDaoSqls {
	public static final String SELECT_PRICE_INFO_BY_PRODUCT_ID
	= "SELECT pp.price_type_name, pp.price, pp.discount_rate " 
	+ "FROM product AS p "
	+ "JOIN product_price AS pp ON p.id = :productId AND p.id = pp.product_id";
}
