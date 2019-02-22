/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao.sqls;

/**
* @author  : 이승수
*/
public class CommentSqls {
	public static final String INSERT_COMMENT = "INSERT INTO "
		+ "reservation_user_comment"
		+ "(product_id"
		+ ", reservation_info_id"
		+ ", score"
		+ ", comment"
		+ ", create_date"
		+ ", modify_date) "
		+ "VALUES"
		+ "(:productId"
		+ ", :reservationInfoId"
		+ ", :score"
		+ ", :comment"
		+ ", NOW()"
		+ ", NOW())";
}
