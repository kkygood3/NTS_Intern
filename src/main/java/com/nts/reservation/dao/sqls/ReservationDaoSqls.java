package com.nts.reservation.dao.sqls;

/**
 * Copyright 2019 NAVER Corp.
 * All rights reserved.
 * Except in the case of internal use for NAVER,
 * unauthorized use of redistribution of this software are strongly prohibited. 
 * 
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */

public class ReservationDaoSqls {
	public static final String INSERT_RESERVATION_RESERVATION_INFO = "INSERT INTO reservation_info ("
		+ "product_id"
		+ ", display_info_id"
		+ ", reservation_name"
		+ ", reservation_tel"
		+ ", reservation_email"
		+ ", reservation_date"
		+ ", create_date"
		+ ", modify_date)"
		+ " VALUES ("
		+ " :productId"
		+ ", :displayInfoId"
		+ ", :reservationName"
		+ ", :reservationTel"
		+ ", :reservationEmail"
		+ ", :reservationYearMonthDay"
		+ ", NOW()"
		+ ", NOW() )";

	public static final String INSERT_RESERVATION_PRICE = "INSERT INTO reservation_info_price ("
		+ "reservation_info_id"
		+ ", product_price_id"
		+ ", count"
		+ ")"
		+ " VALUES ("
		+ " :reservationInfoId"
		+ ", :productPriceId"
		+ ", :count)";

	public static final String SELECT_RESERVATIONS = "SELECT * FROM( "
		+ "(SELECT ri.id as id"
		+ ", SUM(rip.count * pp.price) as totalPrice"
		+ " from reservation_info ri"
		+ " INNER JOIN reservation_info_price rip ON ri.id = rip.reservation_info_id"
		+ " INNER JOIN product_price pp ON rip.product_price_id = pp.id AND ri.reservation_email = :email"
		+ " GROUP BY ri.id) as table1"
		+ " INNER JOIN "
		+ "(SELECT id AS id, product_id AS productId"
		+ ", display_info_id AS displayInfoId"
		+ ", reservation_name AS reservationName"
		+ ", reservation_tel AS reservationTelephone"
		+ ", reservation_email AS reservationEmail"
		+ ", reservation_date AS reservationDate"
		+ ", cancel_flag AS cancelFlag"
		+ ", create_date AS createDate"
		+ ", modify_date AS modifyDate FROM reservation_info WHERE reservation_email = :email) "
		+ "AS table2 ON table1.id = table2.id"
		+ ");";

	public static final String SELECT_RESERVATIONS_COUNT = "SELECT count(id) FROM reservation_info WHERE reservation_email = :email";
}
