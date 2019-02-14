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

/**
 * /api/products/{displayInfoId}/review 요청에 대응
 */
@Repository
public class ReviewResponseDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReviewResponse> rowMapper = BeanPropertyRowMapper.newInstance(ReviewResponse.class);

	public ReviewResponseDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	/**
	 * review 페이지를 로드할 때 필요한 정보를 조회
	 * @param displayInfoId - 조회할 displayInfo table의 ID
	 * @param pagingLimit - 한 페이지에 출력할 item 개수
	 */
	public List<ReviewResponse> selectDetailPageById(int displayInfoId, int pagingLimit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("displayInfoId", displayInfoId);
		params.put("pagingLimit", pagingLimit);
		List<ReviewResponse> reviewList = jdbc.query(ReviewResponseQueries.SELECT_REVIEW, params, rowMapper);

		return reviewList;
	}
}
