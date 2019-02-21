//package com.nts.reservation.dao.sql;
//
//public class ReservationPageInfoDaoSqls {
//	public static final String SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID
//	= "SELECT d.product_id, d.id AS display_info_id, p.description, d.place_name, d.opening_hours, pf.save_file_name AS main_image_name, adddate(now(), interval floor(rand()*5) day) AS reservation_date "
//	+ "FROM display_info AS d "
//	+ "JOIN product AS p ON d.id = :displayInfoId AND d.product_id = p.id "
//	+ "JOIN product_image AS pi ON pi.type = 'ma' AND p.id = pi.product_id "
//	+ "JOIN file_info AS pf ON pf.id = pi.file_id";
//	
//	public static final String SELECT_RESERVATION_DISPLAY_LIST_ITEM_BY_RESERVATION_EMAIL
//	= "SELECT r.id AS reservation_id, p.description, r.reservation_date, r.cancel_flag AS canceled, d.place_name, sum(pp.price*rp.count) AS price, r.modify_date "
//	+ "FROM reservation_info AS r "
//	+ "INNER JOIN product AS p "
//	+ "	ON r.reservation_email = :reservationEmail "
//	+ "	AND r.product_id = p.id "
//	+ "INNER JOIN display_info AS d ON r.display_info_id = d.id "
//	+ "INNER JOIN reservation_info_price AS rp ON rp.reservation_info_id = r.id "
//	+ "INNER JOIN product_price AS pp ON rp.product_price_id = pp.id "
//	+ "GROUP BY r.id "
//	+ "LIMIT :start, :limit";
//	
//	public static final String CONDITION_CONFIRM = "WHERE r.cancel_flag = false AND r.reservation_date > now()";
//	public static final String CONDITION_USED = "WHERE r.cancel_flag = false AND r.reservation_date < now()";
//	public static final String CONDITION_CANCEL = "WHERE r.cancel_flag = true";
//
//	public static final String UPDATE_CANCEL_FLAG_TO_FALSE_BY_RESERVATION_INFO_ID
//	= "UPDATE reservation_info SET cancel_flag = true, modify_date = now() WHERE id = :reservationInfoId AND reservation_email = :reservationEmail";
//}
