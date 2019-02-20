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

	public static final String SELECT_RESERVATION_INFO_ID = "SELECT id "
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

	public static final String SELECT_RESERVATIONS = "SELECT "
		+ "ri.id"
		+ ", ri.product_id"
		+ ", ri.display_info_id"
		+ ", ri.reservation_name"
		+ ", ri.reservation_tel"
		+ ", ri.reservation_email"
		+ ", ri.reservation_date"
		+ ", ri.cancel_flag AS cancel_yn"
		+ ", ri.create_date"
		+ ", ri.modify_date "
		+ ", SUM(rp.count * pp.price) AS total_price "
		+ " FROM reservation_info AS ri "
		+ " INNER JOIN reservation_info_price AS rp"
		+ " ON ri.id = rp.reservation_info_id"
		+ " INNER JOIN product_price AS pp"
		+ " ON rp.product_price_id = pp.id "
		+ " WHERE ri.reservation_email = :userEmail "
		+ " GROUP BY ri.id";

	public static final String UPDATE_RESERVATION_CANCEL_FLAG = "UPDATE "
		+ "reservation_info "
		+ "SET "
		+ "cancel_flag = 1 "
		+ "WHERE "
		+ "id = :reservationInfoId";
}