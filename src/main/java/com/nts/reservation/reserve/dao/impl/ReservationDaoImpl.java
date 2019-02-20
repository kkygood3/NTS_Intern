package com.nts.reservation.reserve.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.reserve.dao.ReservationDao;
import com.nts.reservation.reserve.dao.sqls.ReservationDaoSqls;
import com.nts.reservation.reserve.dto.ReservationInfo;
import com.nts.reservation.reserve.dto.ReservationParam;
import com.nts.reservation.reserve.dto.ReservationPrice;

@Repository
public class ReservationDaoImpl implements ReservationDao{
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfo> reservationInfoRowMapper = BeanPropertyRowMapper.newInstance(ReservationInfo.class);
	private RowMapper<ReservationPrice> reservationPriceRowMapper = BeanPropertyRowMapper.newInstance(ReservationPrice.class);

	public ReservationDaoImpl(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * 
	 * @param reservationParam
	 * @return reservationInfoId auto increment로 생성된 primary key
	 */
	@Override
	public int insertReservationInfo(ReservationParam reservationParam) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("displayInfoId", reservationParam.getDisplayInfoId())
			.addValue("productId", reservationParam.getProductId())
			.addValue("reservationEmail", reservationParam.getReservationEmail())
			.addValue("reservationName", reservationParam.getReservationName())
			.addValue("reservationTel", reservationParam.getReservationTel())
			.addValue("reservationDate", reservationParam.getReservationDate());
		KeyHolder keyHolder = new GeneratedKeyHolder(); 
		jdbc.update(ReservationDaoSqls.INSERT_RESERVATION_INFO, params, keyHolder);
		return keyHolder.getKey().intValue();
	}
	@Override
	public int insertProductPrice(ReservationPrice reservationPrice) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("reservationInfoId", reservationPrice.getReservationInfoId())
			.addValue("productPriceId", reservationPrice.getProductPriceId())
			.addValue("count", reservationPrice.getCount());
		return jdbc.update(ReservationDaoSqls.INSERT_RESERVATION_PRICE, params);
	}
	
	/**
	 * 해당 email로 예약된 모든 reservation 정보를 조회
	 * @param reservationEmail 조회할 email
	 */
	@Override
	public List<ReservationInfo> selectReservationInfos(String reservationEmail){
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("reservationEmail", reservationEmail);
		return jdbc.query(ReservationDaoSqls.SELECT_ALL_RESERVATION_INFOS, params, reservationInfoRowMapper);
	}

	/**
	 * reservation ID를 사용하여 1개의 reservation 정보를 조회
	 * @param reservationInfoId reservation_info(table)의 id
	 * @return
	 */
	@Override
	public ReservationInfo selectReservationInfo(int reservationInfoId) {
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("reservationInfoId", reservationInfoId);
		return DataAccessUtils.singleResult(jdbc.query(ReservationDaoSqls.SELECT_RESERVATION_INFO, params, reservationInfoRowMapper));
	}
	
	/**
	 * reservation ID를 사용하여 reservation price를 조회
	 * @param reservationInfoId reservation_info(table)의 id
	 */
	@Override
	public List<ReservationPrice> selectReservationPrices(int reservationInfoId){
		SqlParameterSource params = new MapSqlParameterSource()
			.addValue("reservationInfoId", reservationInfoId);
		return jdbc.query(ReservationDaoSqls.SELECT_RESERVATION_PRICES, params, reservationPriceRowMapper);
	}
	
	@Override
	public int selectReservationTotalPrice(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(ReservationDaoSqls.SELECT_TOTAL_PRICE, param, Integer.class);
	}
	
	@Override
	public int cancelReservation(int reservationInfoId) {
		Map<String, ?> param = Collections.singletonMap("reservationInfoId", reservationInfoId);
		return jdbc.update(ReservationDaoSqls.CANCEL_RESERVATION, param);
	}
	
	
}
