/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_FILE_INFO;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_RESERVATION_INFO;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_RESERVATION_INFO_PRICE;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_USER_COMMENT;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.INSERT_USER_COMMENT_IMAGE;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_COUNT_FINISH_RESERVATION_BY_EMAIL_AND_ID;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_COUNT_RESERVATION_BY_EMAIL;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_FILE_INFO_BY_COMMENT_IMAGE_ID;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_RESERVATION_INFO_BY_EMAIL;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.SELECT_RESERVATION_INFO_BY_ID;
import static com.nts.reservation.dao.sql.ReservationDaoSqls.UPDATE_CANCEL_RESERVATION_BY_RESERVATION_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.FileDto;
import com.nts.reservation.dto.ReservationInfoDto;
import com.nts.reservation.dto.request.ReservationPriceRequestDto;
import com.nts.reservation.dto.request.ReservationRequestDto;

/**
 * @author 육성렬
 */
@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfoDto> reservationRowMapper = BeanPropertyRowMapper
		.newInstance(ReservationInfoDto.class);
	private RowMapper<FileDto> fileRowMapper = BeanPropertyRowMapper
		.newInstance(FileDto.class);

	public ReservationDao(DataSource dataSource) {
		jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * @desc 예약 정보 DB에 추가.
	 * @param reservation
	 * @return
	 */
	public Long insertReservation(ReservationRequestDto reservation) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);
		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION_INFO, params, holder);
		return holder.getKey().longValue();
	}

	/**
	 * @desc 예약된 전시 티켓 DB에 추가.
	 * @param reservationInfoId
	 * @param productPriceId
	 * @param count
	 * @return
	 */
	public int insertReservationPrice(long reservationInfoId, long productPriceId, int count) {
		Map<String, Object> map = new HashMap<>();
		map.put("reservationInfoId", reservationInfoId);
		map.put("productPriceId", productPriceId);
		map.put("count", count);
		return jdbc.update(INSERT_RESERVATION_INFO_PRICE, map);
	}

	/**
	 * @desc 예약된 전시 티켓들 DB에 추가.
	 * @param priceList
	 * @return
	 */
	public int[] insertReservationPrices(List<ReservationPriceRequestDto> priceList) {
		SqlParameterSource[] batchs = SqlParameterSourceUtils.createBatch(priceList.toArray());
		return jdbc.batchUpdate(INSERT_RESERVATION_INFO_PRICE, batchs);
	}

	/**
	 * @desc 이메일에 해당하는 예약 목록 조회
	 * @param email
	 * @return
	 */
	public List<ReservationInfoDto> selectReservationsByEmail(String email) {
		Map<String, Object> map = Collections.singletonMap("email", email);
		return jdbc.query(SELECT_RESERVATION_INFO_BY_EMAIL, map, reservationRowMapper);
	}

	/**
	 * @param reservationInfoId
	 * @return
	 */
	public ReservationInfoDto selectReservation(Long reservationInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", reservationInfoId);
		return jdbc.queryForObject(SELECT_RESERVATION_INFO_BY_ID, map, reservationRowMapper);
	}

	/**
	 * @desc 이메일에 해당하는 예약 목록 갯수 조회
	 * @param email
	 * @return
	 */
	public int countReservationsByEmail(String email) {
		Map<String, Object> map = Collections.singletonMap("email", email);
		return jdbc.queryForObject(SELECT_COUNT_RESERVATION_BY_EMAIL, map, Integer.class);
	}

	/**
	 * @desc 이메일에 해당하는 예약 목록 갯수 조회
	 * @param email
	 * @return
	 */
	public int countFinishReservationsByEmailAndId(Long reservationInfoId, String email) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("id", reservationInfoId);
		return jdbc.queryForObject(SELECT_COUNT_FINISH_RESERVATION_BY_EMAIL_AND_ID, map, Integer.class);
	}

	/**
	 * @desc 예약 취소 업데이트.
	 * @param reservationInfoId
	 * @return
	 */
	public Integer updateCancelReservation(Long reservationInfoId) {
		Map<String, Object> map = Collections.singletonMap("id", reservationInfoId);
		int resultRows = jdbc.update(UPDATE_CANCEL_RESERVATION_BY_RESERVATION_ID, map);
		return resultRows;
	}

	public Long insertFileInfo(FileDto file) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(file);

		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_FILE_INFO, params, holder);
		return holder.getKey().longValue();
	}

	public Long insertUserComment(Long productId, Long reservationInfoId, Integer score, String comment) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("productId", productId)
			.addValue("reservationInfoId", reservationInfoId)
			.addValue("score", score)
			.addValue("comment", comment);

		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_USER_COMMENT, params, holder);
		return holder.getKey().longValue();
	}

	public Long insertUserCommentImage(Long reservationInfoId, Long reservationUserCommentId, Long fileId) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("reservationInfoId", reservationInfoId)
			.addValue("reservationUserCommentId", reservationUserCommentId)
			.addValue("fileId", fileId);

		KeyHolder holder = new GeneratedKeyHolder();
		jdbc.update(INSERT_USER_COMMENT_IMAGE, params, holder);
		return holder.getKey().longValue();
	}

	public FileDto selectCommentImageById(Long commentImageId) {
		Map<String, Object> map = Collections.singletonMap("imageId", commentImageId);
		return jdbc.queryForObject(SELECT_FILE_INFO_BY_COMMENT_IMAGE_ID, map, fileRowMapper);
	}
}
