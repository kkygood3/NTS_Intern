package com.nts.reservation.dao.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.detail.DetailResponse;
import com.nts.reservation.queries.detail.DetailResponseQueries;

@Repository
public class DetailResponseDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailResponse> rowMapper = BeanPropertyRowMapper.newInstance(DetailResponse.class);

	public DetailResponseDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<DetailResponse> selectDetailResponsePageByDisplayInfoId(int displayInfoId, int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("pagingLimit", pagingLimit);
		List<DetailResponse> detailList = jdbc.query(DetailResponseQueries.SELECT_DETAIL, params, rowMapper);

		return detailList;
	}
}
