package com.nts.reservation.dao.detail;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.detail.DisplayInfo;
import com.nts.reservation.queries.detail.DisplayInfoQueries;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo selectDisplayInfoById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(DisplayInfoQueries.SELECT_DISPLAY_INFO, params, rowMapper).get(0);
	}

}
