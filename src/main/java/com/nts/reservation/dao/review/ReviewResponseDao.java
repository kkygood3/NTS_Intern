package com.nts.reservation.dao.review;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.review.ReviewResponse;
import com.nts.reservation.queries.review.ReviewResponseQueries;

@Repository
public class ReviewResponseDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReviewResponse> rowMapper = BeanPropertyRowMapper.newInstance(ReviewResponse.class);

	public ReviewResponseDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ReviewResponse> selectDetailPageById(int displayInfoId, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("limit", limit);
		List<ReviewResponse> reviewList = jdbc.query(ReviewResponseQueries.SELECT_REVIEW, params, rowMapper);

		return reviewList;
	}
}
