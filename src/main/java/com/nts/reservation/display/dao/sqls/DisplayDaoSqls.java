/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.display.dao.sqls;

public class DisplayDaoSqls {
	public static final String GET_DISPLAY_BY_ID = "SELECT category.id as 'categoryId', category.name as 'categoryName', display_info.create_date as 'createDate', display_info.id as 'displayInfoId', display_info.email as 'email', display_info.homepage as 'homepage', display_info.modify_date as 'modiftyDate', display_info.opening_hours as 'openingHours', display_info.place_lot as 'placeLot', display_info.place_name as 'placeName', display_info.place_street as 'placeStreet', product.content as 'productContent', product.description as 'productDescription', product.event as 'productEvent', product.id as 'productId', display_info.tel as 'telephone',  COUNT(reservation_user_comment.id) 'commentCount' " + 
		"FROM display_info " + 
		"INNER JOIN product ON display_info.product_id = product.id " + 
		"INNER JOIN category ON product.category_id = category.id " + 
		"LEFT JOIN reservation_user_comment ON reservation_user_comment.product_id = product.id " +
		"WHERE display_info.id = :displayInfoId";
	
	public static final String GET_DISPLAY_IMAGE_BY_ID = "SELECT file_info.content_type 'contentType', file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', file_info.id 'fileInfoId', file_info.file_name 'fileName', file_info.modify_date 'modifyDate', display_info.id 'displayInfoId', display_info_image.id 'displayInfoImageId', file_info.save_file_name 'saveFileName' " + 
		"FROM display_info " + 
		"INNER JOIN display_info_image ON display_info_image.display_info_id = display_info.id " + 
		"INNER JOIN file_info ON file_info.id = display_info_image.file_id " + 
		"WHERE display_info.id = :displayInfoId";
}
