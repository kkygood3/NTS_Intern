/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DisplayInfoQuerys {
	public static final String SELECT_DISPLAY_INFO_BY_DISPLAY_INFO_ID = "SELECT product.category_id 'categoryId', category.name 'categoryName', display_info.create_date 'createDate', display_info.id 'displayInfoId', display_info.email 'email', display_info.homepage 'homepage', display_info.modify_date 'modifyDate', display_info.opening_hours 'openingHours', display_info.place_lot 'placeLot', display_info.place_name 'placeName', display_info.place_street 'placeStreet', product.content 'productContent', product.description 'productDescription', product.event 'productEvent', product.id 'productId', display_info.tel 'telephone'"
		+ "FROM display_info"
		+ "INNER JOIN product ON display_info.product_id = product.id"
		+ "INNER JOIN category ON display_info.product_id = category.id"
		+ "WHERE display_info.id = :displayInfoId";

	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_DISPLAY_INFO_ID = "SELECT file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', display_info_image.display_info_id 'displayInfoId', display_info_image.id 'displayInfoImageId', display_info_image.file_id 'fileId', file_info.file_name 'fileName', file_info.modify_date 'modifyDate', file_info.save_file_name 'saveFileName'"
		+ "FROM display_info_image"
		+ "INNER JOIN file_info ON display_info_image.file_id = file_info.id"
		+ "WHERE display_info_image.display_info_id = :displayInfoId";
}
