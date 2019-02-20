/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sql;

public class ReserveSqls {
	public static final String SELECT_RESERVE_DISPLAY_INFO = 
		"SELECT" + 
		" d.id," + 
		" p.description AS product_description," + 
		" d.opening_hours," + 
		" d.place_name," + 
		" d.place_street," + 
		" f.save_file_name AS product_image" + 
		" FROM display_info AS d" + 
		" INNER JOIN product AS p ON d.product_id = p.id" + 
		" INNER JOIN product_image AS pi ON d.product_id = pi.product_id AND pi.type = 'th'" + 
		" INNER JOIN file_info AS f ON pi.file_id = f.id" + 
		" WHERE d.id = :displayInfoId";
	
	public static final String SELECT_RESERVE_PRICE = 
		"SELECT" + 
		" pp.price," + 
		" pp.price_type_name," + 
		" ROUND(pp.discount_rate,0) AS discount_rate" + 
		" FROM display_info AS d" + 
		" INNER JOIN product_price AS pp ON d.product_id = pp.product_id" + 
		" WHERE d.id = :displayInfoId";
	
	
	public static final String INSERT_RESERVE =
		"INSERT" + 
		" INTO reservation_info(" + 
		"  product_id," + 
		"  display_info_id," + 
		"  reservation_name," + 
		"  reservation_tel," + 
		"  reservation_email," + 
		"  reservation_date," + 
		"  cancel_flag," + 
		"  create_date," + 
		"  modify_date" + 
		" )" + 
		" SELECT" + 
		"  p.id," + 
		"  d.id," + 
		"  :name," + 
		"  :telephone," + 
		"  :email," + 
		"  :reservationDate," + 
		"  0," + 
		"  NOW()," + 
		"  NOW()" + 
		"  FROM display_info AS d" + 
		"  INNER JOIN product AS p ON d.product_id = p.id" + 
		"  WHERE d.id = :displayInfoId" + 
		"  GROUP BY p.id";
	
	public static final String INSERT_RESERVE_PRICE =
		"INSERT" + 
		" INTO reservation_info_price(" + 
		"  reservation_info_id," + 
		"  product_price_id," + 
		"  count" + 
		" )" + 
		" SELECT" + 
		"  :reservationInfoId," + 
		"  pp.id," + 
		"  :count" + 
		"  FROM display_info AS d" + 
		"  INNER JOIN product_price AS pp ON d.product_id = pp.product_id" + 
		"  WHERE pp.price_type_name = :type AND d.id = :displayInfoId";
		
}
