/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.queries.detail;

public class ProductPriceQueries {
	public static final String SELECT_PRODUCT_PRICE = 
		"SELECT create_date, discount_rate, modify_date, price, price_type_name, product_id,id AS product_price_id"
		+ " FROM product_price"
		+ " WHERE product_price.product_id = :productId";
}
