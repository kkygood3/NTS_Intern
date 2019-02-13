/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class DisplayInfoDaoSqls {
	public static final String SELECT_DISPLAY_INFO_BY_ID = "SELECT display.product_id,"
		+ " display.id AS display_info_id,"
		+ " display.opening_hours,"
		+ " display.place_name,"
		+ " display.place_lot,"
		+ " display.place_street,"
		+ " display.tel AS telephone,"
		+ " display.homepage,"
		+ " display.email,"
		+ " display.create_date,"
		+ " display.modify_date,"
		+ " product.content AS product_content,"
		+ " product.event AS product_event,"
		+ " product.description AS product_description,"
		+ " category.id AS category_id,"
		+ " category.name AS category_name"
		+ " FROM display_info AS display"
		+ " INNER JOIN product"
		+ " ON product.id = display.product_id"
		+ " INNER JOIN category"
		+ " ON product.category_id = category.id"
		+ " WHERE display.id = :id";

	public static final String SELECT_COMMENT_BY_DISPLAY_INFO_ID = "SELECT comment.comment,"
		+ " comment.id AS comment_id,"
		+ " reservation.reservation_date,"
		+ " reservation.reservation_email,"
		+ " comment.reservation_info_id,"
		+ " reservation.reservation_name,"
		+ " reservation.reservation_tel AS reservation_telephone,"
		+ " reservation.product_id,"
		+ " comment.score,"
		+ " reservation.create_date,"
		+ " reservation.modify_date"
		+ " FROM reservation_user_comment AS comment"
		+ " INNER JOIN reservation_info AS reservation"
		+ " ON reservation.id = comment.reservation_info_id"
		+ " WHERE reservation.display_info_id = :id";

	public static final String SELECT_COMMENT_AVG_SCORE_BY_DISPLAY_INFO_ID = "SELECT AVG(comment.score)"
		+ " FROM reservation_user_comment AS comment"
		+ " INNER JOIN reservation_info AS reservation"
		+ " ON reservation.id = comment.reservation_info_id"
		+ " WHERE reservation.display_info_id = :id";

	public static final String SELECT_COMMENT_IMAGE_BY_ID = "SELECT file.content_type,"
		+ " file.create_date,"
		+ " file.delete_flag,"
		+ " file.id AS file_id,"
		+ " file.file_name,"
		+ " image.id AS image_id,"
		+ " file.modify_date,"
		+ " image.reservation_info_id,"
		+ " image.reservation_user_comment_id,"
		+ " file.save_file_name"
		+ " FROM reservation_user_comment_image AS image"
		+ " INNER JOIN file_info AS file"
		+ " ON file.id = image.file_id"
		+ " WHERE image.reservation_user_comment_id = :id"
		+ " ORDER BY file.create_date DESC";

	public static final String SELECT_DISPLAY_INFO_IMAGE_BY_ID = "SELECT file.content_type,"
		+ " file.create_date,"
		+ " file.delete_flag,"
		+ " file.modify_date,"
		+ " file.save_file_name,"
		+ " file.file_name,"
		+ " file.id AS file_id,"
		+ " image.display_info_id,"
		+ " image.id AS display_info_image_id"
		+ " FROM  display_info_image AS image"
		+ " INNER JOIN file_info AS file"
		+ " ON image.file_id = file.id"
		+ " WHERE image.display_info_id = :id";

	public static final String SELECT_PRODUCT_IMAGE_BY_ID = "SELECT file.content_type,"
		+ " file.create_date,"
		+ " file.delete_flag,"
		+ " file.id AS file_info_id,"
		+ " file.file_name,"
		+ " file.modify_date,"
		+ " image.product_id,"
		+ " image.id AS product_image_id,"
		+ " file.save_file_name,"
		+ " image.type FROM file_info AS file,"
		+ " product_image AS image"
		+ " WHERE image.file_id = file.id"
		+ " AND image.type != \"th\""
		+ " AND image.product_id = :id";

	public static final String SELECT_PRODUCT_PRICE_BY_ID = "SELECT price.create_date,"
		+ " price.modify_date,"
		+ " price.discount_rate,"
		+ " price.price,"
		+ " price.price_type_name,"
		+ " price.product_id,"
		+ " price.id AS product_price_id"
		+ " FROM product_price AS price"
		+ " WHERE price.product_id = :id";

	public static final String SELECT_MAIN_PRODUCT_IMAGE_BY_ID = "SELECT file_info.content_type,"
		+ " file_info.create_date,"
		+ " file_info.delete_flag,"
		+ " file_info.id AS file_info_id,"
		+ " file_info.file_name,"
		+ " file_info.modify_date,"
		+ " image.product_id,"
		+ " image.id AS product_image_id,"
		+ " file_info.save_file_name,"
		+ " image.type FROM file_info,"
		+ " product_image AS image"
		+ " WHERE image.file_id = file_info.id"
		+ " AND image.type != \"th\""
		+ " AND image.product_id = :id"
		+ " Limit 1";
}
