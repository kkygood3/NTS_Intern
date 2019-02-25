/**
 * Copyright 2019 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.nts.dao.comment;

import static com.nts.sqls.comment.CommentSqls.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.dto.comment.Comment;
import com.nts.dto.comment.CommentImage;
import com.nts.dto.file.FileInfo;

/**
 * @author 전연빈
 */
@Repository
public class CommentRepository {
	
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<Comment> commentRowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private RowMapper<CommentImage> commentImageRowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<FileInfo> fileInfoRowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);
	
	public CommentRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	/**
	 * @desc productId 별 comment
	 * @param productId
	 * @return list comment
	 */
	public List<Comment> selectCommentByProductId(int productId){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("productId", productId);
		
		return namedParameterJdbcTemplate.query(SELECT_COMMENT_BY_PRODUCT_ID,params,commentRowMapper);
	}
	
	/**
	 * @desc commentId 별 commentImage 가져오기
	 * @param commentId
	 * @return commentImage
	 */
	public CommentImage selectCommentImagesByCommentId(int commentId){
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("commentId", commentId);
		
		return namedParameterJdbcTemplate.queryForObject(SELECT_COMMENT_IMAGES_BY_RESERVATION_USER_COMMENT_ID, params, commentImageRowMapper);
	}
	
	/**
	 * @desc prouductId 별 comment 평균 점수
	 * @param productId
	 * @return averageScore
	 */
	public double selectCommentAverageByProductId(int productId) {
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("productId", productId);
		
		return namedParameterJdbcTemplate.queryForObject(SELECT_COMMENTS_AVERAGE_BY_PRODUCT_ID, params, Double.class);
	}
	
	/**
	 * @desc commentImage file정보 불러오기
	 * @param commentId
	 * @return fileInfo
	 * @throws FileNotFoundException 
	 */
	public FileInfo selectFileInfoByCommentId(int commentId) throws FileNotFoundException {
		Map<String, Object> params = new HashMap<>();
		params.put("commentId", commentId);
		try {
			return namedParameterJdbcTemplate.queryForObject(SELECT_FILE_INFO_BY_COMMENT_ID, params, fileInfoRowMapper);
		} catch(EmptyResultDataAccessException e) {
			throw new FileNotFoundException("파일을 찾을수 없습니다.");
		}
	}
}
