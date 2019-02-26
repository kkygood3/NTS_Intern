/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.comment.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.comment.model.Comment;
import com.nts.reservation.comment.model.CommentListInfo;
import com.nts.reservation.comment.model.WritedComment;
import com.nts.reservation.common.exception.UnauthenticateException;
import com.nts.reservation.file.model.FileInfo;

import static com.nts.reservation.comment.dao.querys.CommentQuerys.*;

@Repository
public class CommentDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	private final RowMapper<Comment> commentMapper;

	private final RowMapper<CommentListInfo> commentListInfoMapper;

	private final RowMapper<FileInfo> fileInfoMapper;

	@Autowired
	public CommentDao(NamedParameterJdbcTemplate jdbcTemplate, RowMapper<Comment> commentMapper,
		RowMapper<CommentListInfo> commentListInfoMapper, RowMapper<FileInfo> fileInfoMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.commentMapper = commentMapper;
		this.commentListInfoMapper = commentListInfoMapper;
		this.fileInfoMapper = fileInfoMapper;
	}

	/**
	 * 특정 display의 전체 comment 목록 반환
	 */
	public List<Comment> selectCommentList(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.query(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_ALL, param,
			selectCommentListResultSetExtractor());
	}

	/**
	 * 특정 display의 갯수제한된 comment 목록 반환
	 */
	public List<Comment> selectLimitedCommentList(int displayInfoId, int limitCount) {
		Map<String, Integer> param = new HashMap<>();
		param.put("displayInfoId", displayInfoId);
		param.put("limitCount", limitCount);

		return jdbcTemplate.query(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_LIMIT, param,
			selectCommentListResultSetExtractor());
	}

	/**
	 * 특정 display의 comment 관련정보 반환 (평균점수, 총댓글 갯수)
	 */
	public CommentListInfo selectCommentListInfo(int displayInfoId) {
		Map<String, Integer> param = Collections.singletonMap("displayInfoId", displayInfoId);
		return jdbcTemplate.queryForObject(SELECT_PRODUCT_DISPLAY_COMMENT_LIST_INFO, param, commentListInfoMapper);
	}

	/**
	 * comment 저장, 예약없이 댓글 작성시 UnauthenticateException 발생
	 */
	public int insertComment(WritedComment writedComment) {

		SqlParameterSource param = new MapSqlParameterSource()
			.addValues(new ObjectMapper().convertValue(writedComment, Map.class));

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(INSERT_RESERVATION_USER_COMMENT, param, keyHolder);
		try {
			return keyHolder.getKey().intValue();
		} catch (NullPointerException e) {
			throw new UnauthenticateException("can not write this comment, because no permission", e);
		}

	}

	/**
	 * comment 작성시 첨부한 image 파일정보 저장
	 */
	public int insertReservationUserCommentImageInfo(int reservationId, int reservationUserCommentId, int fileId) {

		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("reservationId", reservationId)
			.addValue("reservationUserCommentId", reservationUserCommentId)
			.addValue("fileId", fileId);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(INSERT_RESERVATION_USER_COMMENT_IMAGE_INFO, param, keyHolder);

		return keyHolder.getKey().intValue();
	}

	/**
	 * 저장된 fileinfo 조회
	 */
	public FileInfo selectCommentImageSaveFileInfo(int commentImageId) {
		Map<String, Integer> param = Collections.singletonMap("commentImageId", commentImageId);
		return jdbcTemplate.queryForObject(SELECT_COMMENT_SAVE_FILE_NAME, param, fileInfoMapper);
	}

	/**
	 * db에서 얻어온 comment image id를 comment 객체 img id list에 저장, 그리고 comment들을  list로 반환하게 하는 ResultSetExtractor 객체를 생성
	 */
	private ResultSetExtractor<List<Comment>> selectCommentListResultSetExtractor() {
		return new ResultSetExtractor<List<Comment>>() {
			@Override
			public List<Comment> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<Integer, Comment> commentMap = new HashMap<>();
				List<Comment> commentList = new ArrayList<>();
				while (rs.next()) {
					int commentId = rs.getInt("comment_id");

					Comment comment = commentMapper.mapRow(rs, rs.getRow());
					if (!commentMap.containsKey(commentId)) {
						commentMap.put(commentId, comment);
						commentList.add(comment);
					}

					String commentImageId = rs.getString("comment_image_id");
					if (commentImageId != null) {
						commentMap.get(commentId).getCommentImageIdList().add(commentImageId);
					}

				}
				return commentList;
			}
		};
	}
}
