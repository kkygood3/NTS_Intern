/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.sqls.reservation;

public class ReservationCommentSqls {
	public static final String INSERT_INTO_FILE_INFO = 
	"INSERT INTO file_info (file_name, save_file_name, content_type, delete_flag, create_date, modify_date) " + 
	"VALUES (:fileName, :saveFileName, :contentType, 0, NOW(), NOW())";
	
	public static final String INSERT_INTO_RESERVATION_USER_COMMENT_IMAGE =
	"INSERT INTO reservation_user_comment_image ( reservation_info_id, reservation_user_comment_id, file_id) " + 
	"VALUES (:reservationInfoId, :reservationUserCommentId, :fileId)";
	
	public static final String INSERT_INTO_RESERVATION_USER_COMMENT = 
	"INSERT INTO reservation_user_comment ( product_id, reservation_info_id, score, comment, create_date, modify_date) " + 
	"VALUES (:productId, :reservationInfoId,:score ,:comment, NOW(), NOW())";
}
