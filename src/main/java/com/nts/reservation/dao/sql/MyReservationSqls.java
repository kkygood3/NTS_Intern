/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class MyReservationSqls {
	public static final String SELECT_MY_RESERVATION_INFO =
		"SELECT" + 
		" r.id AS reservation_info_id," + 
		" rp.id AS reservation_info_price_id," + 
		" r.reservation_name," + 
		" r.display_info_id," + 
		" r.reservation_date," + 
		" p.description AS product_description," + 
		" d.place_name," + 
		" d.opening_hours," + 
		" SUM(ROUND(pp.price * (100-pp.discount_rate) * rp.count/100,0)) AS price," + 
		" r.cancel_flag" + 
		" FROM reservation_info AS r" + 
		" INNER JOIN display_info AS d ON r.display_info_id = d.id" + 
		" INNER JOIN product AS p ON r.product_id = p.id" + 
		" INNER JOIN reservation_info_price AS rp ON r.id = rp.reservation_info_id" + 
		" INNER JOIN product_price AS pp ON rp.product_price_id = pp.id" + 
		" WHERE r.reservation_email = :reservationEmail" + 
		" GROUP BY reservation_info_id";
	
	public static final String CANCEL_RESERVE =
		"UPDATE" + 
		" reservation_info AS r" + 
		" SET" + 
		" cancel_flag = 1," +
		" modify_date = NOW()" +
		" WHERE r.id = :reservationInfoId" +
		" AND r.reservation_email = :reservationEmail";
}
