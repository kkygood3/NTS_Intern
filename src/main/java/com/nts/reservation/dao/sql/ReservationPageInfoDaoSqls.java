package com.nts.reservation.dao.sql;

public class ReservationPageInfoDaoSqls {
	public static final String SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID
	= "SELECT d.product_id, d.id AS display_info_id, p.description, d.place_name, d.opening_hours, pf.save_file_name AS main_image_name "
	+ "FROM display_info AS d "
	+ "JOIN product AS p ON d.id = :displayInfoId AND d.product_id = p.id "
	+ "JOIN product_image AS pi ON pi.type = 'ma' AND p.id = pi.product_id "
	+ "JOIN file_info AS pf ON pf.id = pi.file_id";
}
