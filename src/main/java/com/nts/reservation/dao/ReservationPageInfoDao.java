package com.nts.reservation.dao;

import static com.nts.reservation.dao.sql.ReservationPageInfoDaoSqls.SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID;
import static com.nts.reservation.property.Const.DISPLAY_INFO_ID;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.ReservationPageInfo;

@Repository
public class ReservationPageInfoDao extends BaseDao {
	private RowMapper<ReservationPageInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationPageInfo.class);

	ReservationPageInfoDao(DataSource dataSource) {
		super(dataSource);
	}

	public ReservationPageInfo selectReservationPageInfoByDisplayInfoId(long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put(DISPLAY_INFO_ID, displayInfoId);
		return getJdbc().queryForObject(SELECT_RESERVATION_PAGE_INFO_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
