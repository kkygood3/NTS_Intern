package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationPageInfoDaoSqls.SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationInfo;
import com.nts.reservation.dto.ReservationPageInfo;

@Repository
public class ReservationInfoDao extends BaseDao {
	private RowMapper<ReservationPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationPageInfo.class);
	private SimpleJdbcInsert insertAction;
	ReservationInfoDao(DataSource dataSource) {
		super(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
	}

	public ReservationPageInfo selectReservationPageInfoByDisplayInfoId(long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

	public long insertReservationInfo(ReservationInfo reservationInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}
}
