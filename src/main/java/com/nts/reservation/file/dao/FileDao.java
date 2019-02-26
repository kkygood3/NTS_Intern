/**
 * Copyright 2015 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.nts.reservation.file.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nts.reservation.file.model.FileInfo;

import static com.nts.reservation.file.dao.querys.FileQuerys.*;

@Repository
public class FileDao {

	private final NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	public FileDao(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * fileinfo 저장
	 */
	public int insertFileInfo(FileInfo fileInfo) {
		SqlParameterSource param = new MapSqlParameterSource()
			.addValues(new ObjectMapper().convertValue(fileInfo, Map.class));

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(INSERT_FILE_INFO, param, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
