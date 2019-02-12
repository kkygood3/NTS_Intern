/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.display.dao.sqls;

public class DisplayDaoSqls {
	public static final String GET_DISPLAY_BY_ID = "SELECT category.id as 'categoryId', category.name as 'categoryName', display_info.create_date as 'createDate', display_info.id as 'displayInfoId', display_info.email as 'email', display_info.homepage as 'homepage', display_info.modify_date as 'modiftyDate', display_info.opening_hours as 'openingHours', display_info.place_lot as 'placeLot', display_info.place_name as 'placeName', display_info.place_street as 'placeStreet', product.content as 'productContent', product.description as 'productDescription', product.event as 'productEvent', product.id as 'productId', display_info.tel as 'telephone' " + 
		"FROM display_info " + 
		"INNER JOIN product ON display_info.product_id = product.id " + 
		"INNER JOIN category ON product.category_id = category.id " + 
		"WHERE display_info.id = :displayInfoId";
}
