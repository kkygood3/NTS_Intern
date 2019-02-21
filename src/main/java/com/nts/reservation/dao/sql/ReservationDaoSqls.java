/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao.sql;

/**
 * @author 육성렬
 */
public class ReservationDaoSqls {
	public static final String INSERT_RESERVATION_INFO = "INSERT INTO reservation_info"
		+ " (product_id"
		+ ", display_info_id"
		+ ", reservation_name"
		+ ", reservation_tel"
		+ ", reservation_email"
		+ ", reservation_date"
		+ ", create_date"
		+ ", modify_date)"
		+ " VALUES (:productId"
		+ ", :displayInfoId"
		+ ", :reservationName"
		+ ", :reservationTel"
		+ ", :reservationEmail"
		+ ", :reservationDate"
		+ ", NOW()"
		+ ", NOW())";

	public static final String INSERT_RESERVATION_INFO_PRICE = "INSERT INTO reservation_info_price"
		+ " ( reservation_info_id"
		+ ", product_price_id"
		+ ", count )"
		+ " VALUES ("
		+ " :reservationInfoId"
		+ ", :productPriceId"
		+ ", :count)";

	public static final String SELECT_RESERVATION_INFO_BY_EMAIL = "SELECT SUM(pd_price.price * rv_price.count) AS total_price,"
		+ "reservation.cancel_flag AS cancel_yn"
		+ ", reservation.create_date"
		+ ", reservation.display_info_id"
		+ ", reservation.modify_date"
		+ ", reservation.product_id"
		+ ", reservation.reservation_name"
		+ ", reservation.reservation_email"
		+ ", reservation.reservation_date"
		+ ", reservation.reservation_tel"
		+ ", reservation.id AS reservation_info_id"
		+ " FROM reservation_info AS reservation"
		+ " INNER JOIN reservation_info_price AS rv_price ON reservation.id = rv_price.reservation_info_id"
		+ " INNER JOIN product_price AS pd_price ON pd_price.id = rv_price.product_price_id"
		+ " WHERE reservation.reservation_email = :email"
		+ " GROUP BY rv_price.reservation_info_id";

	public static final String SELECT_COUNT_RESERVATION_BY_EMAIL = "SELECT COUNT(reservation.id)"
		+ " FROM reservation_info AS reservation"
		+ " WHERE reservation.reservation_email = :email";

	public static final String SELECT_COUNT_FINISH_RESERVATION_BY_EMAIL_AND_ID = "SELECT COUNT(reservation.id)"
		+ " FROM reservation_info AS reservation"
		+ " WHERE reservation.reservation_email = :email"
		+ " AND reservation.id = :id"
		+ " AND reservation.cancel_flag = false"
		+ " AND reservation.reservation_date < NOW()";

	public static final String UPDATE_CANCEL_RESERVATION_BY_RESERVATION_ID = "UPDATE reservation_info"
		+ " SET cancel_flag = true"
		+ " WHERE id = :id";

	public static final String INSERT_FILE_INFO = "INSERT INTO file_info"
		+ " (file_name"
		+ ", save_file_name"
		+ ", content_type"
		+ ", delete_flag"
		+ ", create_date"
		+ ", modify_date)"
		+ " VALUES (:fileName"
		+ ", :saveFileName"
		+ ", :contentType"
		+ ", false"
		+ ", NOW()"
		+ ", NOW())";

	public static final String INSERT_USER_COMMENT = "INSERT INTO reservation_user_comment"
		+ " ( product_id"
		+ ", reservation_info_id"
		+ ", score"
		+ ", comment"
		+ ", create_date"
		+ ", modify_date)"
		+ " VALUES(:productId"
		+ ", :reservationInfoId"
		+ ", :score"
		+ ", :comment"
		+ ", NOW()"
		+ ", NOW())";

	public static final String INSERT_USER_COMMENT_IMAGE = "INSERT INTO reservation_user_comment_image"
		+ " ( reservation_info_id"
		+ ", reservation_user_comment_id"
		+ ", file_id)"
		+ " VALUES( :reservationInfoId"
		+ ", :reservationUserCommentId"
		+ ", :fileId)";

	public static final String SELECT_RESERVATION_INFO_BY_ID = "SELECT SUM(pd_price.price * rv_price.count) AS total_price,"
		+ "reservation.cancel_flag AS cancel_yn"
		+ ", reservation.create_date"
		+ ", reservation.display_info_id"
		+ ", reservation.modify_date"
		+ ", reservation.product_id"
		+ ", reservation.reservation_name"
		+ ", reservation.reservation_email"
		+ ", reservation.reservation_date"
		+ ", reservation.reservation_tel"
		+ ", reservation.id AS reservation_info_id"
		+ " FROM reservation_info AS reservation"
		+ " INNER JOIN reservation_info_price AS rv_price ON reservation.id = rv_price.reservation_info_id"
		+ " INNER JOIN product_price AS pd_price ON pd_price.id = rv_price.product_price_id"
		+ " WHERE reservation.id = :id"
		+ " GROUP BY rv_price.reservation_info_id";

	public static final String SELECT_FILE_INFO_BY_COMMENT_IMAGE_ID = "SELECT file.file_name"
		+ ", file.save_file_name"
		+ ", file.content_type"
		+ " FROM file_info AS file"
		+ " INNER JOIN reservation_user_comment_image AS image"
		+ " ON image.file_id = file.id"
		+ " WHERE image.id = :imageId";
}
