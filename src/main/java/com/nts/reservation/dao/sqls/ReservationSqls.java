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
		+ ", :reservationTel"
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
		+ "AND reservation_tel = :reservationTel "
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

	public static final String AVAILABLE = "AND reservation_date > NOW()";
	public static final String EXPIRED = "AND reservation_date < NOW()";
	public static final String GET_RESERVATIONS = "SELECT * "
		+ "FROM reservation_info "
		+ "WHERE reservation_email = :userEmail "
		+ "AND cancel_flag = :cancelFlag ";

	public static final String GET_TOTAL_PRICE = "SELECT SUM(count * price) "
		+ "FROM reservation_info_price "
		+ "INNER JOIN product_price "
		+ "ON reservation_info_price.product_price_id = product_price.id "
		+ "WHERE reservation_info_id = :reservationInfoId";

	public static final String CANCEL_RESERVATION = "UPDATE reservation_info SET cancel_flag = 1 WHERE id = :reservationInfoId";
}
