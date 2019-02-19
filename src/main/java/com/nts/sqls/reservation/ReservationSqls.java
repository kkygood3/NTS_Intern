/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.sqls.reservation;

/**
 * @author 전연빈
 */
public class ReservationSqls {
	public static final String SELECT_RESERVATION_INFO_BY_RESERVATION_EMAIL = 
	"SELECT IF(r_i.cancel_flag,'true','false') as cancel_yn, r_i.create_date as create_date, r_i.display_info_id as display_info_id, r_i.reservation_date as reservation_date, r_i.reservation_email as reservation_email, r_i.id as reservation_info_id, r_i.reservation_name as reservation_name, r_i.reservation_tel as reservation_telephone, SUM(r_i_p.count*p_p.price) as total_price " + 
	"FROM reservation_info r_i  INNER JOIN reservation_info_price r_i_p INNER JOIN product_price p_p " + 
	"ON r_i.id = reservation_info_id AND r_i_p.product_price_id = p_p.id " + 
	"WHERE r_i.reservation_email = :reservationEmail " + 
	"GROUP BY r_i.id";
	
	public static final String INSERT_INTO_RESERVATION_INFO =
	"INSERT INTO reservation_info (product_id, display_info_id, reservation_name, reservation_tel, reservation_email, reservation_date, create_date, modify_date) "+
	"VALUES (:productId, :displayInfoId, :reservationName, :reservationTelephone, :reservationEmail, NOW(), NOW(), NOW() )";
	
	public static final String INSERT_INTO_RSERVATION_INFO_PRICE =
	"INSERT INTO reservation_info_price ( reservation_info_id, product_price_id, count) "+
	"VALUES (:reservationInfoId, :productPriceId, :count)";
	
	public static final String UPDATE_RESERVATION_INFO_CANCEL = 
	"UPDATE reservation_info SET cancel_flag = 1 " +
	"WHERE id = :reservationId";
}
