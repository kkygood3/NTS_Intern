/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao.query;

/**
 * @Author Duik Park, duik.park@nts-corp.com
 */
public class CommentDaoQuerys {
	public static final String SELECT_ALL_COMMENT = "SELECT reservation_user_comment.id 'commentId', reservation_info.display_info_id, reservation_user_comment.score 'score', product.description, reservation_user_comment.comment 'comment', reservation_info.reservation_name 'reservationName', reservation_user_comment.create_date 'createDate'"
		+ " FROM display_info"
		+ " INNER JOIN product ON display_info.product_id = product.id"
		+ " INNER JOIN reservation_user_comment ON display_info.product_id = reservation_user_comment.product_id"
		+ " INNER JOIN reservation_info ON reservation_user_comment.reservation_info_id = reservation_info.id"
		+ " LEFT JOIN reservation_user_comment_image ON reservation_user_comment_image.id = reservation_user_comment.reservation_info_id"
		+ " WHERE reservation_info.product_id = :displayInfoId";

	public static final String SELECT_LIMIT_COMMENT = "SELECT reservation_user_comment.id 'commentId', reservation_info.display_info_id, reservation_user_comment.score 'score', product.description, reservation_user_comment.comment 'comment', reservation_info.reservation_name 'reservationName', reservation_user_comment.create_date 'createDate'"
		+ " FROM display_info"
		+ " INNER JOIN product ON display_info.product_id = product.id"
		+ " INNER JOIN reservation_user_comment ON display_info.product_id = reservation_user_comment.product_id"
		+ " INNER JOIN reservation_info ON reservation_user_comment.reservation_info_id = reservation_info.id"
		+ " LEFT JOIN reservation_user_comment_image ON reservation_user_comment_image.id = reservation_user_comment.reservation_info_id"
		+ " WHERE reservation_info.product_id = :displayInfoId"
		+ " LIMIT :start, :limit";

	public static final String SELECT_COMMENT_IMAGE = "SELECT file_info.content_type 'contentType', file_info.create_date 'createDate', file_info.delete_flag 'deleteFlag', reservation_user_comment_image.file_id 'fileId', file_info.file_name 'fileName', reservation_user_comment_image.id 'imageId', file_info.modify_date 'modifyDate', reservation_user_comment_image.reservation_info_id 'reservationInfoId', reservation_user_comment_image.reservation_user_comment_id 'reservationUserCommentId', file_info.save_file_name 'saveFileName'"
		+ " FROM reservation_user_comment_image"
		+ " INNER JOIN file_info ON reservation_user_comment_image.file_id = file_info.id"
		+ " WHERE reservation_user_comment_image.reservation_user_comment_id = :commentId";

	public static final String SELECT_AVERAGE_SCORE = "SELECT ( SUM( score ) / count( reservation_user_comment.id ) )"
		+ " FROM reservation_user_comment"
		+ " INNER JOIN reservation_info ON reservation_user_comment.reservation_info_id = reservation_info.id"
		+ " WHERE reservation_info.product_id = :displayInfoId";
}
