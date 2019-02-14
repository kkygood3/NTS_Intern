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
		+ " :reservation_info_id"
		+ ", :product_price_id"
		+ ", :count)";
}
