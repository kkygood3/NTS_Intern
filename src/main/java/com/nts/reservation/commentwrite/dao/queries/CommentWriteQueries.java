/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.commentwrite.dao.queries;

public class CommentWriteQueries {
	public static final String INSERT_COMMENT =
		"INSERT " +
		"			INTO reservation_user_comment( " +
		"				reservation_info_id, " +
		"				product_id, " +
		"				comment, " +
		"				score, " +
		"				create_date, " +
		"				modify_date)   " +
		"				VALUES(   " +
		"					:reservationInfoId, " +
		"					:productId, " +
		"					:comment, " +
		"					:score, " +
		"					NOW(), " +
		"					NOW());";
}
