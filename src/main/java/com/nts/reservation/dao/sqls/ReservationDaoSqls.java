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
	public static final String INSERT_RESERVATION_RESERVATION_INFO = "INSERT INTO "
		+ "reservation_info ("
		+ "product_id"
		+ ", display_info_id"
		+ ", reservation_name"
		+ ", reservation_tel"
		+ ", reservation_email"
		+ ", reservation_date"
		+ ", create_date"
		+ ", modify_date"
		+ ")"
		+ " VALUES ("
		+ " :productId"
		+ ", :displayInfoId"
		+ ", :reservationName"
		+ ", :reservationTel"
		+ ", :reservationEmail"
		+ ", :reservationYearMonthDay"
		+ ", NOW()"
		+ ", NOW() "
		+ ")";

	public static final String INSERT_RESERVATION_PRICE = "INSERT INTO "
		+ "reservation_info_price ("
		+ "reservation_info_id"
		+ ", product_price_id"
		+ ", count"
		+ ")"
		+ " VALUES ("
		+ " :reservationInfoId"
		+ ", :productPriceId"
		+ ", :count"
		+ ")";

	public static final String SELECT_RESERVATIONS = "SELECT "
		+ "ri.id AS id"
		+ ", ri.product_id AS productId"
		+ ", ri.display_info_id AS displayInfoId"
		+ ", ri.reservation_name AS reservationName"
		+ ", ri.reservation_tel AS reservationTelephone"
		+ ", ri.reservation_email AS reservationEmail"
		+ ", ri.reservation_date AS reservationDate"
		+ ", ri.cancel_flag AS cancelFlag"
		+ ", ri.create_date AS createDate"
		+ ", ri.modify_date AS modifyDate"
		+ ", SUM(rip.count * pp.price) as totalPrice"
		+ " FROM "
		+ "reservation_info ri"
		+ " INNER JOIN "
		+ "reservation_info_price rip ON ri.id = rip.reservation_info_id"
		+ " INNER JOIN "
		+ "product_price pp ON rip.product_price_id = pp.id AND ri.reservation_email = :email"
		+ " GROUP BY ri.id";

	public static final String SELECT_RESERVATIONS_COUNT = "SELECT "
		+ "count(id) "
		+ "FROM reservation_info "
		+ "WHERE reservation_email = :email ";

	public static final String UPDATE_RESERVATION_CANCEL_FLAG = "UPDATE reservation_info"
		+ " SET cancel_flag = true "
		+ " WHERE id = :reservationId";

	public static final String SELECT_RESERVATION_BY_EMAIL_AND_RSV_ID = "SELECT "
		+ "ri.id AS id"
		+ ", ri.product_id AS productId"
		+ ", ri.display_info_id AS displayInfoId"
		+ ", ri.reservation_name AS reservationName"
		+ ", ri.reservation_tel AS reservationTelephone"
		+ ", ri.reservation_email AS reservationEmail"
		+ ", ri.reservation_date AS reservationDate"
		+ ", ri.cancel_flag AS cancelFlag"
		+ ", ri.create_date AS createDate"
		+ ", ri.modify_date AS modifyDate"
		+ " FROM "
		+ "reservation_info ri"
		+ " WHERE "
		+ "ri.reservation_email = :email"
		+ " AND ri.id = :reservationId"
		+ " AND ri.cancel_flag = false"
		+ " AND ri.reservation_date < NOW()";
}
