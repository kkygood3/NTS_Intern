package com.nts.dao.commentdao;

public class CommentDaoSqls {
	public static final String SELECT_AVERAGE_SCORE_BY_DISPLAY_INFO_ID = "SELECT "
																			+ "IFNULL( ROUND( AVG(score), 1), '0.0') as average_score "
																	   + "FROM "
																			+ "  reservation_user_comment "
																			+ ", display_info "
																	   + "WHERE "
																			+ "    display_info.id = :displayInfoId "
																			+ "and display_info.product_id = reservation_user_comment.product_id ";
	
	public static final String SELECT_COMMENT_IMAGES_BY_DISPLAY_INFO_ID = " SELECT "
																			+ "CONCAT( "
																			+ "  '{'"
																			+ ",   '\"contentType\":'			  ,CHAR(34), file_info.content_type, CHAR(34)"
																			+ ", ', \"createDate\":' 			  ,CHAR(34), file_info.create_date, CHAR(34)"
																			+ ", ', \"deleteFlag\":' 			  ,CHAR(34), file_info.delete_flag, CHAR(34)"
																			+ ", ', \"fileName\":  ' 			  ,CHAR(34), file_info.file_name, CHAR(34)"
																			+ ", ', \"modifyDate\":' 			  ,CHAR(34), file_info.modify_date, CHAR(34)"
																			+ ", ', \"productImageUrl\":'		  ,CHAR(34), file_info.save_file_name, CHAR(34)"
																			+ ", ', \"fileId\":'				  ,CHAR(34), reservation_user_comment_image.file_id, CHAR(34)"
																			+ ", ', \"id\":'					  ,CHAR(34), reservation_user_comment_image.id , CHAR(34)"
																			+ ", ', \"reservationInfoId\":'		  ,CHAR(34), reservation_user_comment_image.reservation_info_id, CHAR(34)"
																			+ ", ', \"reservationUserCommnetId\":',CHAR(34), reservation_user_comment_image.reservation_user_comment_id, CHAR(34)"
																			+ ", '}'"
																			+ ")"
																		+ "FROM "
																			+ "  reservation_user_comment_image "
																			+ ", file_info "
																		+ "WHERE "
																			+ "    reservation_user_comment.id = reservation_user_comment_image.reservation_user_comment_id "
																			+ "and reservation_user_comment_image.file_id = file_info.id "
																			+ "LIMIT 1";
	
	public static final String SELECT_COMMENTS_BY_DISPLAY_INFO_ID = "SELECT "
																	+ "  reservation_user_comment.comment "
																	+ ", reservation_user_comment.id "
																	+ ", ("+SELECT_COMMENT_IMAGES_BY_DISPLAY_INFO_ID+") as comment_images "
																	+ ", reservation_info.create_date "
																	+ ", reservation_info.modify_date "
																	+ ", reservation_info.product_id "
																	+ ", reservation_info.reservation_date "
																	+ ", reservation_info.reservation_email "
																	+ ", reservation_user_comment.reservation_info_id "
																	+ ", reservation_info.reservation_name "
																	+ ", reservation_info.reservation_tel "
																	+ ", reservation_user_comment.score "
																  + "FROM "
																  	+ "  reservation_user_comment "
																  	+ ", reservation_info "
																  + "WHERE "
																  	+ "    reservation_info.display_info_id = :displayInfoId "
																  	+ "and reservation_info.product_id = reservation_user_comment.product_id "
																  	+ "and reservation_info.id = reservation_user_comment.reservation_info_id ";



}
