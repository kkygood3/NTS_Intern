/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class ReservationDaoSqls {
	public static final String INSERT_RESERVATION_INFO = "INSERT INTO reservation_info"
		+ " (product_id"
		+ ", display_info_id"
		+ ", reservation_name"
		+ ", reservation_tel"
		+ ", reservation_email"
		+ ", reservation_date"
		+ ", create_date"
		+ ", modify_date)"
		+ " VALUES (:productId"
		+ ", :displayInfoId"
		+ ", :reservationName"
		+ ", :reservationTel"
		+ ", :reservationEmail"
		+ ", :reservationDate"
		+ ", NOW()"
		+ ", NOW())";

	public static final String INSERT_RESERVATION_INFO_PRICE = "INSERT INTO reservation_info_price"
		+ " ( reservation_info_id"
		+ ", product_price_id"
		+ ", count )"
		+ " VALUES ("
		+ " :reservationInfoId"
		+ ", :productPriceId"
		+ ", :count)";

	public static final String SELECT_RESERVATION_INFO_BY_EMAIL = "SELECT SUM(pd_price.price * rv_price.count) AS total_price,"
		+ "reservation.cancel_flag AS cancel_yn"
		+ ", reservation.create_date"
		+ ", reservation.display_info_id"
		+ ", reservation.modify_date"
		+ ", reservation.product_id"
		+ ", reservation.reservation_name"
		+ ", reservation.reservation_email"
		+ ", reservation.reservation_date"
		+ ", reservation.reservation_tel"
		+ ", reservation.id AS reservation_info_id"
		+ " FROM reservation_info AS reservation"
		+ " INNER JOIN reservation_info_price AS rv_price ON reservation.id = rv_price.reservation_info_id"
		+ " INNER JOIN display_info AS display ON reservation.display_info_id = display.id"
		+ " INNER JOIN product ON product.id = reservation.product_id"
		+ " INNER JOIN category ON product.category_id = category.id"
		+ " INNER JOIN product_price AS pd_price ON pd_price.id = rv_price.product_price_id"
		+ " WHERE reservation.reservation_email = :email"
		+ " GROUP BY rv_price.reservation_info_id";

	public static final String SELECT_COUNT_RESERVATION_BY_EMAIL = "SELECT COUNT(reservation.id)"
		+ " FROM reservation_info AS reservation"
		+ " WHERE reservation.reservation_email = :email";

	public static final String UPDATE_CANCEL_RESERVATION_BY_RESERVATION_ID = "UPDATE reservation_info"
		+ " SET cancel_flag = true"
		+ " WHERE id = :id";
}
