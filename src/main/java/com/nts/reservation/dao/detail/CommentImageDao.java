package com.nts.reservation.dao.detail;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dao.queries.CommentImageQueries;
import com.nts.reservation.dto.detail.CommentImage;

@Repository
public class CommentImageDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public CommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public CommentImage selectCommentImageByCommentId(int commentId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("commentId", commentId);
		List<CommentImage> commentImageList = jdbc.query(CommentImageQueries.SELECT_COMMENT_IMAGE, params, rowMapper);
		
		if (commentImageList.size() > 0) {
			return commentImageList.get(0);
		} else {
			return null;
		}
	}
}
