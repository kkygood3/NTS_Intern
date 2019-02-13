package com.nts.reservation.dao.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.detail.DetailExtraImage;
import com.nts.reservation.queries.detail.DetailExtraImageQueries;

@Repository
public class DetailExtraImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailExtraImage> rowMapper = BeanPropertyRowMapper.newInstance(DetailExtraImage.class);

	public DetailExtraImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DetailExtraImage selectDetailExtraImageById(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		List<DetailExtraImage> imageList = jdbc.query(DetailExtraImageQueries.SELECT_EXTRA_IMAGE, params, rowMapper);

		if (imageList.size() > 0) {
			return imageList.get(0);
		} else {
			return null;
		}
	}
}
