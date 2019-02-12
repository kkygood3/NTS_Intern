package com.nts.reservation.dao.detail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.detail.Comment;
import com.nts.reservation.queries.detail.CommentQueries;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectCommentByProductId(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		return jdbc.query(CommentQueries.SELECT_COMMENT, params, rowMapper);
	}

	public Double selectAvgScoreByProductId(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("productId", productId);
		Double avg = jdbc.queryForObject(CommentQueries.SELECT_AVG_SCORE, params, Double.class);

		if (avg == null) {
			avg = 0.0;
		}

		return avg;
	}
}
