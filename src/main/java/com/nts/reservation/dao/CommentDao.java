package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.CommentDaoSqls.INSERT_COMMENT;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.INSERT_COMMENT_IMAGE_INFO;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.INSERT_FILE_INFO;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_COMMENT_COUNT_BY_RESERVATION_ID;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_COMMENT_IMAGE_BY_IMAGE_ID;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_DETAIL_COMMENTS;
import static com.nts.reservation.dao.sqls.CommentDaoSqls.SELECT_DETAIL_COMMENTS_IMAGES_BY_COMMENT_ID;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.dto.comment.Comment;
import com.nts.reservation.dto.comment.CommentImage;
import com.nts.reservation.dto.comment.CommentParam;

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

	public CommentImage selectCommentImageByImageId(Long commentImageId) {
		Map<String, Long> params = new HashMap<>();
		params.put("commentImageId", commentImageId);
		return jdbc.queryForObject(SELECT_COMMENT_IMAGE_BY_IMAGE_ID, params,
			BeanPropertyRowMapper.newInstance(CommentImage.class));
	}

	public Long CountCommentByReservationId(Long reservationId) {
		Map<String, Long> params = new HashMap<>();
		params.put("reservationId", reservationId);
		Long i = jdbc.queryForObject(SELECT_COMMENT_COUNT_BY_RESERVATION_ID, params, Long.class);
		return i;
	}

	public Long insertComment(CommentParam commentParam) {
		KeyHolder holder = new GeneratedKeyHolder();

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("comment", commentParam.getComment());
		params.addValue("reservationInfoId", commentParam.getReservationInfoId());
		params.addValue("score", commentParam.getScore());
		params.addValue("productId", commentParam.getProductId());

		jdbc.update(INSERT_COMMENT, params, holder, new String[] {"id"});

		return holder.getKey().longValue();
	}

	public Long insertFileInfo(MultipartFile file, Long commentId, CommentParam commentParam) {
		KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("fileName", file.getOriginalFilename());
		params.addValue("saveFileName", "img_uploaded/" + commentId + "/" + file.getOriginalFilename());
		params.addValue("contentType", file.getContentType());

		jdbc.update(INSERT_FILE_INFO, params, holder, new String[] {"id"});

		return holder.getKey().longValue();
	}

	public Long insertCommentImageInfo(Long fileId, Long reservationInfoId, Long commentId) {
		KeyHolder holder = new GeneratedKeyHolder();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("reservationInfoId", reservationInfoId);
		params.addValue("fileId", fileId);
		params.addValue("reservationUserCommentId", commentId);
		jdbc.update(INSERT_COMMENT_IMAGE_INFO, params, holder, new String[] {"id"});
		return commentId;
	}

}
