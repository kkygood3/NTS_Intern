package com.nts.reservation.dao.sql;

public class ReservationPageInfoDaoSqls {
	public static final String SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID
	= "SELECT d.product_id, d.id AS display_info_id, p.description, d.place_name, d.opening_hours, pf.save_file_name AS main_image_name "
	+ "FROM display_info AS d "
	+ "JOIN product AS p ON d.id = :displayInfoId AND d.product_id = p.id "
	+ "JOIN product_image AS pi ON pi.type = 'ma' AND p.id = pi.product_id "
	+ "JOIN file_info AS pf ON pf.id = pi.file_id";
	
	public static final String SELECT_RESERVATION_DISPLAY_LIST_ITEM_BY_RESERVATION_EMAIL
	= "SELECT r.id AS reservation_id, p.description, r.reservation_date, r.cancel_flag, d.place_name, sum(pp.price) AS price "
	+ "FROM reservation_info AS r "
	+ "INNER JOIN product AS p "
	+ "	ON r.reservation_email = :reservationEmail "
	+ "	AND (r.reservation_date BETWEEN date_add(now(), interval :start month) AND date_add(now(), interval :end month)) "
	+ "	AND r.product_id = p.id "
	+ "INNER JOIN display_info AS d ON r.display_info_id = d.id "
	+ "INNER JOIN product_price AS pp ON pp.product_id = p.id "
	+ "GROUP BY d.id";
}
