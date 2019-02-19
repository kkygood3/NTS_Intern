package com.nts.reservation.reserve.dao.sqls;

public class ReservationDaoSqls {
	public static final String INSERT_RESERVATION_INFO =
		"INSERT INTO reservation_info (product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, create_date, modify_date ) " + 
		"VALUES (:productId, :displayInfoId, :reservationName, :reservationTel, :reservationEmail, now(), now(), now())";
	
	public static final String INSERT_RESERVATION_PRICE =
		"INSERT INTO reservation_info_price(reservation_info_id, product_price_id, count) "
		+ "VALUES(:reservationInfoId, :productPriceId, :count)";
	
	public static final String SELECT_ALL_RESERVATION_INFOS =
		"SELECT cancel_flag 'cancelYn', create_date 'createDate', display_info_id 'displayInfoId', modify_date 'modifyDate', product_id 'productId', reservation_date 'reservationDate', reservation_email 'reservationEmail', id 'reservationInfoId', reservation_name 'reservationName', reservation_tel 'reservationTel' " + 
		"FROM reservation_info " + 
		"WHERE reservation_info.reservation_email = :reservationEmail";
	
	public static final String SELECT_RSERVATION_INFO = 
		"SELECT cancel_flag 'cancelYn', create_date 'createDate', display_info_id 'displayInfoId', modify_date 'modifyDate', product_id 'productId', reservation_date 'reservationDate', reservation_email 'reservationEmail', id 'reservationInfoId', reservation_name 'reservationName', reservation_tel 'reservationTel' " + 
		"FROM reservation_info " + 
		"WHERE id = :reservationInfoId";
	
	public static final String SELECT_RSERVATION_PRICES = 
		"SELECT count 'count', product_price_id 'productPriceId', reservation_info_id 'reservationInfoId', id 'productId' " + 
		"FROM reservation_info_price " + 
		"WHERE reservation_info_id = :reservationInfoId";
}
