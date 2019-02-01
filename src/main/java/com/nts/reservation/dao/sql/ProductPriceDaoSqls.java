/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class ProductPriceDaoSqls {
	public static final String SELECT_PRODUCT_PRICE_BY_ID = "SELECT price.create_date,"
		+ " price.modify_date,"
		+ " price.discount_rate,"
		+ " price.price,"
		+ " price.price_type_name,"
		+ " price.product_id,"
		+ " price.id AS product_price_id"
		+ " FROM product_price AS price"
		+ " WHERE price.product_id = :id";
}
