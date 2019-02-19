/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.reservation.dao.queries;

public class ReservationQueries {
	public static final String SELECT_RESERVATION_PRICES =
		"SELECT " +
		"	reservation_info_price.count AS count, " +
		"	product_price.id AS product_price_id, " +
		"	reservation_info.id AS reservation_info_id, " +
		"	reservation_info_price.id AS reservation_info_price_id " +
		"FROM " +
		"	reservation_info_price " +
		"		INNER JOIN " +
		"	product_price ON product_price.id = reservation_info_price.product_price_id " +
		"		INNER JOIN " +
		"	reservation_info ON reservation_info.id = reservation_info_price.reservation_info_id " +
		"WHERE " +
		"	product_price.product_id = :productId;";

	public static final String SELECT_RESERVATION_INFO =
		"SELECT " +
		"	reservation_info.create_date AS create_date, " +
		"	reservation_info.display_info_id AS display_info_id, " +
		"	reservation_info.modify_date AS modify_date, " +
		"	reservation_info.product_id AS product_id, " +
		"	reservation_info.reservation_date AS reservation_date, " +
		"	reservation_info.reservation_email AS reservation_email, " +
		"	reservation_info.id AS reservation_info_id, " +
		"	reservation_info.reservation_name AS reservation_name, " +
		"	reservation_info.reservation_tel AS reservation_telephone " +
		"FROM " +
		"	reservation_info " +
		"WHERE " +
		"	reservation_info.reservation_email = :reservationEmail;";

	public static final String SELECT_RESERVATION_RESPONSE =
		"SELECT  " +
		"	reservation_info.cancel_flag AS cancle_yn, " +
		"	reservation_info.create_date AS create_date, " +
		"	reservation_info.display_info_id AS display_info_id, " +
		"	reservation_info.modify_date AS modify_date, " +
		"	reservation_info.product_id AS product_id, " +
		"	reservation_info.reservation_date AS reservation_date, " +
		"	reservation_info.reservation_email AS reservation_email, " +
		"	reservation_info.id AS reservation_info_id, " +
		"	reservation_info.reservation_name AS reservation_name, " +
		"	reservation_info.reservation_tel AS reservation_telephone " +
		"FROM " +
		"	reservation_info " +
		"WHERE " +
		"	reservation_info.reservation_email = :reservationEmail;";

	public static final String SELECT_RESERVATION_SIZE =
		"SELECT " +
		" 	COUNT(*) " +
		"FROM " +
		" 	reservation_info " +
		"WHERE " +
		" 	reservation_info.reservation_email = :reservationEmail;";

	public static final String SELECT_TOTAL_PRICE =
		"SELECT " +
		"	SUM(reservation_info_price.count * ROUND(product_price.price * (100 - product_price.discount_rate) / 100, -2)) AS a " +
		"FROM " +
		"	reservation_info_price " +
		"		INNER JOIN " +
		"	reservation_info ON reservation_info.id = reservation_info_price.reservation_info_id " +
		"		INNER JOIN " +
		"	product_price ON product_price.id = reservation_info_price.product_price_id " +
		"WHERE " +
		"	reservation_info.reservation_email = :reservationEmail " +
		"		AND reservation_info.product_id = :productId;";
}
