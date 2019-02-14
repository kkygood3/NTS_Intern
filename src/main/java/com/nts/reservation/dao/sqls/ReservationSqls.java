/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sqls;

/**
* @author  : 이승수
*/
public class ReservationSqls {
	public static final String INSERT_RESERVATION_INFO = "INSERT INTO "
		+ "reservation_info"
		+ "(product_id"
		+ ", display_info_id"
		+ ", reservation_name"
		+ ", reservation_tel"
		+ ", reservation_email"
		+ ", reservation_date"
		+ ", create_date"
		+ ", modify_date) "
		+ "VALUES"
		+ "(:productId"
		+ ", :displayInfoId"
		+ ", :reservationName"
		+ ", :reservationTelephone"
		+ ", :reservationEmail"
		+ ", :reservationDate"
		+ ", NOW()"
		+ ", NOW())";

	public static final String GET_RESERVATION_INFO_ID = "SELECT id "
		+ "FROM "
		+ "reservation_info "
		+ "WHERE "
		+ "product_id = :productId "
		+ "AND display_info_id = :displayInfoId "
		+ "AND reservation_name = :reservationName "
		+ "AND reservation_tel = :reservationTelephone "
		+ "AND reservation_email = :reservationEmail "
		+ "AND reservation_date = :reservationDate ";

	public static final String INSERT_RESERVATION_INFO_PRICE = "INSERT INTO "
		+ "reservation_info_price"
		+ "(reservation_info_id"
		+ ", product_price_id" + ", count) "
		+ "VALUES"
		+ "(:reservationInfoId"
		+ ", :productPriceId"
		+ ", :count)";

	public static final String GET_RESERVATIONS = "SELECT * "
		+ "FROM reservation_info "
		+ "WHERE reservation_email = :userEmail "
		+ "AND cancel_flag = :cancelFlag";
}
