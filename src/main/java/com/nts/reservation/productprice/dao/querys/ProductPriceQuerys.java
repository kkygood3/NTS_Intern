/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.productprice.dao.querys;

public class ProductPriceQuerys {
	public static final String SELECT_PRODUCT_PRICE_LIST = "select pp.id as product_price_id"
		+ ", pp.price_type_name as price_type"
		+ ", pp.price as price"
		+ ", pp.discount_rate as discount_rate "
		+ "from display_info d "
		+ "inner join product p "
		+ "on d.product_id = p.id "
		+ "inner join product_price pp "
		+ "on p.id = pp.product_id "
		+ "where d.id = :displayInfoId";
}
