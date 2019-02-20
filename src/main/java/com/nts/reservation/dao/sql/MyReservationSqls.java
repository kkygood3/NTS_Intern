/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class MyReservationSqls {
	public static final String SELECT_MY_RESERVATION_INFO =
		"SELECT" + 
		" reservation_info.id AS reservation_info_id," + 
		" reservation_info_price.id AS reservation_info_price_id," + 
		" reservation_info.reservation_name," + 
		" reservation_info.display_info_id," + 
		" reservation_info.reservation_date," +
		" product.description AS product_description," + 
		" display_info.place_name," + 
		" display_info.opening_hours," + 
		" SUM(ROUND(product_price.price * (100-product_price.discount_rate) * reservation_info_price.count/100,0)) AS price," + 
		" reservation_info.cancel_flag" + 
		" FROM reservation_info" + 
		" INNER JOIN display_info ON reservation_info.display_info_id = display_info.id" + 
		" INNER JOIN product ON reservation_info.product_id = product.id" + 
		" INNER JOIN reservation_info_price ON reservation_info.id = reservation_info_price.reservation_info_id" + 
		" INNER JOIN product_price ON reservation_info_price.product_price_id = product_price.id" + 
		" WHERE reservation_info.reservation_email = :reservationEmail" + 
		" GROUP BY reservation_info_id";
	
	public static final String CANCEL_RESERVE =
		"UPDATE" + 
		" reservation_info" + 
		" SET" + 
		" cancel_flag = 1" + 
		" WHERE id = :reservationInfoId";
}
