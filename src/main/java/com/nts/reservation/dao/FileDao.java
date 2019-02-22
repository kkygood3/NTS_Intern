/**
 * Copyright 2015 NaverCorp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.dao;

import static com.nts.reservation.dao.sqls.FileSqls.INSERT_FILE_INFO;
import static com.nts.reservation.dao.sqls.FileSqls.SELECT_FILE_BY_ID;
import static com.nts.reservation.dao.sqls.FileSqls.SELECT_FILE_ID_BY_PRODUCT_ID;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.dto.FileInfo;

/**
* @author  : 이승수
*/
@Repository
public class FileDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileInfo> rowMapper = BeanPropertyRowMapper.newInstance(FileInfo.class);

	public FileDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public FileInfo selectFileInfo(Integer fileId) {
		Map<String, Integer> param = Collections.singletonMap("fileId", fileId);
		return jdbc.queryForObject(SELECT_FILE_BY_ID, param, rowMapper);
	}

	public int selectFileIdByProductId(Integer productId) {
		Map<String, Integer> param = Collections.singletonMap("productId", productId);
		return jdbc.queryForObject(SELECT_FILE_ID_BY_PRODUCT_ID, param, Integer.class);
	}

	public int insertFileInfo(FileInfo fileInfo) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_FILE_INFO, new BeanPropertySqlParameterSource(fileInfo), keyHolder);
		return keyHolder.getKey().intValue();
	}
}
