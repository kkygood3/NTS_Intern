/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.displayInfo.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class DisplayInfoQuerys {
	public static final String SELECT_DISPLAY_INFO = "SELECT display_info.id 'displayInfoId', product.description 'productDescription', product.content 'productContent', display_info.place_name, display_info.place_lot, display_info.place_street, display_info.tel 'telephone', product_file_info.save_file_name 'productImage', IFNULL(ROUND(AVG(reservation_user_comment.score),1),0), COUNT(reservation_user_comment.id), display_file_info.save_file_name 'displayInfoImage'"
		+ " FROM display_info"
		+ " INNER JOIN product ON display_info.product_id = product.id"
		+ " INNER JOIN product_image ON product.id = product_image.product_id AND product_image.type = 'th'"
		+ " LEFT JOIN reservation_user_comment ON product.id = reservation_user_comment.product_id"
		+ " INNER JOIN display_info_image ON display_info.id = display_info_image.display_info_id"
		+ " INNER JOIN file_info AS product_file_info ON product_image.file_id = product_file_info.id"
		+ " INNER JOIN file_info AS display_file_info ON display_info_image.file_id = display_file_info.id"
		+ " WHERE display_info.id = :displayInfoId";

	public static final String SELECT_DISPLAY_INFO_IMAGE = "SELECT file_info.content_type 'contentType', file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', display_info_image.display_info_id 'displayInfoId', display_info_image.id 'displayInfoImageId', display_info_image.file_id 'fileId', file_info.file_name 'fileName', file_info.modify_date 'modifyDate', file_info.save_file_name 'saveFileName'"
		+ " FROM display_info_image"
		+ " INNER JOIN file_info ON display_info_image.file_id = file_info.id"
		+ " WHERE display_info_image.display_info_id = :displayInfoId";
}
