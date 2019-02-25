/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.reservation;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.dto.file.FileInfo;
import com.nts.dto.reservation.ReservationCommentParam;

import static com.nts.sqls.reservation.ReservationCommentSqls.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 전연빈
 */
@Repository
public class ReservationCommentRepository {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public ReservationCommentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * @desc file 정보 삽입
	 * @param fileInfo
	 */
	public int insertIntoFileInfo(FileInfo fileInfo) {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(fileInfo);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(INSERT_INTO_FILE_INFO, sqlParameterSource, keyHolder);

		return keyHolder.getKey().intValue();
	}

	/**
	 * @desc reservationComment 삽입 
	 * @param reservationCommentParam
	 */
	public int insertIntoReservationUserComment(ReservationCommentParam reservationCommentParam) {
		SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(reservationCommentParam);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		namedParameterJdbcTemplate.update(INSERT_INTO_RESERVATION_USER_COMMENT, sqlParameterSource, keyHolder);

		return keyHolder.getKey().intValue();
	}

	/**
	 * @desc comment Image 삽입 
	 * @param reservationInfoId
	 * @param reservationUserCommentId
	 * @param fileId
	 */
	public int insertIntoReservationUserCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		Map<String, Object> params = new HashMap<>();

		params.put("reservationInfoId", reservationInfoId);
		params.put("reservationUserCommentId", reservationUserCommentId);
		params.put("fileId", fileId);
		
		return namedParameterJdbcTemplate.update(INSERT_INTO_RESERVATION_USER_COMMENT_IMAGE, params);
	}
}
