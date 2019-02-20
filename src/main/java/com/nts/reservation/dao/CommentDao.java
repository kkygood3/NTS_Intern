package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_COMMENT_IMAGE_BY_IMAGE_ID;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_DETAIL_COMMENTS;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_DETAIL_COMMENTS_IMAGES_BY_COMMENT_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.common.CommentImage;
import com.nts.reservation.dto.detail.Comment;

/**
 * Author: Jaewon Lee, lee.jaewon@nts-corp.com
 *
 */
@Repository
public class CommentDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbc;

	public List<Comment> selectComments(Long displayInfoId) {
		Map<String, Long> params = new HashMap<>();
		params.put("display_info_id", displayInfoId);
		return jdbc.query(SELECT_DETAIL_COMMENTS, params,
			BeanPropertyRowMapper.newInstance(Comment.class));
	}

	public List<CommentImage> selectCommentsImagesByCommentId(Long CommentId) {
		Map<String, Long> params = new HashMap<>();
		params.put("commentId", CommentId);
		return jdbc.query(SELECT_DETAIL_COMMENTS_IMAGES_BY_COMMENT_ID, params,
			BeanPropertyRowMapper.newInstance(CommentImage.class));
	}

	public CommentImage selectCommentImagebyImageId(Long commentImageId) {
		Map<String, Long> params = new HashMap<>();
		params.put("commentImageId", commentImageId);
		return jdbc.queryForObject(SELECT_COMMENT_IMAGE_BY_IMAGE_ID, params,
			BeanPropertyRowMapper.newInstance(CommentImage.class));
	}
}
