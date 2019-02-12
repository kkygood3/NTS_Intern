/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao.sqls;

public class CommentDaoSqls {
	public static final String GET_ALL_COMMENT = "SELECT reservation_user_comment.id 'commentId', reservation_user_comment.product_id 'productId', reservation_info.id 'reservationInfoId', score 'score', comment, reservation_name 'reservationName', reservation_tel 'reservationTelephone', reservation_email 'reservationEmail', reservation_date 'reservationDate', reservation_info.create_date 'createDate', reservation_info.modify_date 'modifyDate' " + 
		"FROM reservation_user_comment " + 
		"INNER JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id " + 
		"WHERE reservation_info.display_info_id = :displayInfoId " + 
		"ORDER BY reservation_user_comment.id DESC";
	
	public static final String GET_LIMIT_COMMENT = "SELECT reservation_user_comment.id 'commentId', reservation_user_comment.product_id 'productId', reservation_info.id 'reservationInfoId', score 'score', comment, reservation_name 'reservationName', reservation_tel 'reservationTelephone', reservation_email 'reservationEmail', reservation_date 'reservationDate', reservation_info.create_date 'createDate', reservation_info.modify_date 'modifyDate' " + 
		"FROM reservation_user_comment " + 
		"INNER JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id " + 
		"WHERE reservation_info.display_info_id = :displayInfoId " + 
		"ORDER BY reservation_user_comment.id DESC " + 
		"LIMIT 0, :limit";
	
	public static final String GET_COMMENT_IMAGES_BY_COMMENT_ID = "SELECT file_info.content_type 'contentType', file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', file_info.id 'fileId', file_info.file_name 'fileName', reservation_user_comment_image.id 'imageId', file_info.modify_date 'modifyDate',  file_info.save_file_name 'saveFileName' " + 
		"FROM reservation_user_comment " + 
		"INNER JOIN reservation_user_comment_image ON reservation_user_comment_image.reservation_user_comment_id = reservation_user_comment.id " + 
		"INNER JOIN file_info ON file_info.id = reservation_user_comment_image.file_id " + 
		"INNER JOIN display_info_image ON display_info_image.file_id = file_info.id  " + 
		"WHERE display_info_image.display_info_id = :displayInfoId " + 
		"AND reservation_user_comment.id = :commentId";
	
	public static final String GET_COMMENT_AVG_SCORE = "SELECT ROUND(AVG(score), 1) as 'averageScore' " + 
		"FROM reservation_user_comment " + 
		"INNER JOIN product ON product.id = reservation_user_comment.product_id " + 
		"INNER JOIN display_info ON display_info.product_id = product.id " + 
		"WHERE display_info.id = :displayInfoId";
}
