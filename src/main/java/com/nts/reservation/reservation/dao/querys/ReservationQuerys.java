/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.reservation.dao.querys;

public class ReservationQuerys {
	public static final String INSERT_RESERVATION_INFO = "insert into "
		+ "reservation_info values("
		+ "default"
		+ ", :productId"
		+ ", :displayInfoId"
		+ ", :reservationName"
		+ ", :reservationTel"
		+ ", :reservationEmail"
		+ ", :reservationDate"
		+ ", default"
		+ ", now()"
		+ ", now()"
		+ ")";

	public static final String INSERT_RESERVATION_INFO_PRICE = "insert into "
		+ "reservation_info_price values("
		+ "default"
		+ ", :reservationInfoId"
		+ ", :productPriceId"
		+ ", :count"
		+ ")";

	public static final String SELECT_RESERVATION_HISTORY = "select ri.id as reservation_id"
		+ ", p.description as product_description"
		+ ", d.place_name as place_name"
		+ ", d.place_lot as place_lot"
		+ ", d.place_street as place_street"
		+ ", ri.reservation_date as reservation_date"
		+ ", if(cancel_flag = 1, 1, if(ri.reservation_date > now(), 0, 2)) as reservation_status"
		+ ", sum(rp.count * pp.price) as total_price "
		+ "from reservation_info ri "
		+ "inner join product p "
		+ "on ri.product_id = p.id "
		+ "inner join display_info d "
		+ "on ri.display_info_id = d.id "
		+ "inner join reservation_info_price rp "
		+ "on ri.id = rp.reservation_info_id "
		+ "inner join product_price pp "
		+ "on rp.product_price_id = pp.id "
		+ "where ri.reservation_email = :reservationEmail "
		+ "group by ri.id";

	public static final String UPDATE_RESERVATION_CANCEL_FLAG_STRING = "update reservation_info "
		+ "set cancel_flag = :statusCode "
		+ "where id = :reservationId";

}
