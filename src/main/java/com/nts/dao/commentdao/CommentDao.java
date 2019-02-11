package com.nts.dao.commentdao;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.commentdto.Comment;
import com.nts.dto.commentdto.CommentImage;

import static com.nts.dao.commentdao.CommentDaoSqls.*;

@Repository
public class CommentDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);

	public List<Comment> selectCommentsByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENTS_BY_DISPLAY_INFO_ID, param, commentRowMapper);
	}
	
	public List<CommentImage> selectCommentImagesByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.query(SELECT_COMMENT_IMAGES_BY_DISPLAY_INFO_ID, param, commentImageRowMapper);
	}

	public double selectAverageScoreByDisplayInfoId(int displayInfoId) {
		Map<String, ?> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbc.queryForObject(SELECT_AVERAGE_SCORE_BY_DISPLAY_INFO_ID, param, Double.class);
	}
}
