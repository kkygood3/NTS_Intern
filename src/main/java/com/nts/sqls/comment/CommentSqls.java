/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.sqls.comment;

/**
 * @author 전연빈
 */
public class CommentSqls {
	public static final String SELECT_COMMENT_BY_PRODUCT_ID = 
	"SELECT r_c.id AS comment_id, r_c.product_id AS product_id, r_c.reservation_info_id AS reservation_info_id, FLOOR(r_c.score) AS score, r_c.comment AS comment, r_i.reservation_name AS reservation_name, r_i.reservation_tel AS reservation_telephone, r_i.reservation_email AS reservation_email, r_i.reservation_date AS reservation_date, r_i.create_date AS create_date, r_i.modify_date AS modify_date  ,IF(r_c_i.id>0,'true','false') as has_image_file " + 
	"FROM reservation_info r_i INNER JOIN reservation_user_comment r_c  " + 
	"ON r_c.reservation_info_id = r_i.id  " + 
	"LEFT OUTER JOIN reservation_user_comment_image r_c_i " + 
	"ON r_c.id = r_c_i.reservation_user_comment_id " + 
	"WHERE  r_c.product_id = :productId " + 
	"ORDER BY r_c.create_date ASC";
	
	public static final String SELECT_COMMENT_IMAGES_BY_RESERVATION_USER_COMMENT_ID =
	"SELECT f.id AS image_id, r_c.reservation_info_id AS reservation_info_id,r_c.id AS reservation_user_comment_id, f.id AS file_id, f.file_name AS file_name, f.save_file_name AS save_file_name, f.content_type AS content_type, IF(f.delete_flag,'true','false') AS delete_flag, f.create_date AS create_date, f.modify_date AS modify_date " + 
	"FROM reservation_user_comment_image r_c_i INNER JOIN reservation_user_comment r_c INNER JOIN file_info f " + 
	"ON r_c_i.reservation_user_comment_id = r_c.id AND r_c_i.file_id = f.id " + 
	"WHERE r_c.id = :commentId";
	
	public static final String SELECT_COMMENTS_AVERAGE_BY_PRODUCT_ID =
	"SELECT IFNULL(AVG(score),0) AS average_score " + 
	"FROM reservation_user_comment " + 
	"WHERE product_id = :productId";
	
	public static final String SELECT_FILE_INFO_BY_COMMENT_ID =
	"SELECT f_i.file_name, f_i.save_file_name, f_i.content_type " + 
	"FROM reservation_user_comment_image r_u_c_i INNER JOIN file_info f_i " + 
	"ON r_u_c_i.file_id = f_i.id " + 
	"WHERE r_u_c_i.reservation_user_comment_id = :commentId";
}
 