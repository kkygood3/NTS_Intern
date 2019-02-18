/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

/**
 * 예약 정보에 대한 SQL문을 모아둔 클래스
 * @author jinwoo.bae
 */
public class ReservationDaoSqls {
	public static final String SELECT_MY_RESERVATIONS = ""
			+ "SELECT  "
			+ "    ri.id, "
			+ "    ri.product_id, "
			+ "    ri.display_info_id, "
			+ "    ri.reservation_date, "
			+ "    di.place_name, "
			+ "    c.name AS categoryName, "
			+ "    p.description AS productDescription, "
			+ "    SUM(pp.price) AS totalPrice "
			+ "FROM "
			+ "    reservation_info ri "
			+ "        INNER JOIN "
			+ "    product p ON ri.product_id = p.id "
			+ "        INNER JOIN "
			+ "    category c ON p.category_id = c.id "
			+ "        INNER JOIN "
			+ "    display_info di ON ri.display_info_id = di.id "
			+ "        INNER JOIN "
			+ "    reservation_info_price rip ON ri.id = rip.reservation_info_id "
			+ "        INNER JOIN "
			+ "    product_price pp ON rip.product_price_id = pp.id "
			+ "WHERE "
			+ "    ri.reservation_email = :reservationEmail "
			+ "GROUP BY ri.id ";
}
