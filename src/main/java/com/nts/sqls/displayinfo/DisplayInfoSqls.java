/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.sqls.displayinfo;

/**
 * @author 전연빈
 */
public class DisplayInfoSqls {
	public static final String SELECT_DISPLAY_INFO = 
	"SELECT p.id AS product_id , p.category_id AS category_id , d.id AS display_info_id, c.name AS category_name, p.description AS product_description, p.content AS product_content, p.event AS product_event, d.opening_hours AS opening_hours, d.place_name AS place_name, d.place_lot AS place_lot, d.place_street AS place_street, d.tel AS telephone, d.homepage AS homepage, d.email AS email, d.create_date AS create_date, d.modify_date AS modify_date    " + 
	"FROM product p INNER JOIN display_info d INNER JOIN category c " + 
	"ON d.product_id = p.id   AND  p.category_id = c.id " + 
	"WHERE d.id = :displayInfoId";
	
	public static final String SELECT_DISPLAY_INFO_IMAGE =
	"SELECT d_img.id AS display_info_image_id, d.id AS display_info_id, d_img.file_id AS file_id, f.file_name AS file_name, f.save_file_name AS save_file_name, f.content_type AS content_type, IF(f.delete_flag,'true','false') AS delete_flag, f.create_date AS create_date, f.modify_date AS modify_date " + 
	"FROM display_info d INNER JOIN display_info_image d_img INNER JOIN file_info f " + 
	"ON d.id = d_img.display_info_id AND d_img.file_id = f.id " + 
	"WHERE d.id = :displayInfoId";
	
	public static final String CHECK_DISPLAY_INFO_ID_IS_NULL =
	"SELECT IF(COUNT(id),'false','true') " + 
	"FROM display_info " + 
	"WHERE id = :displayInfoId";
}	
