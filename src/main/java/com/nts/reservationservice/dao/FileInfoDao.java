/**
 * Copyright 2019 Naver Corp. All rights Reserved.
 * Naver PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.nts.reservationservice.dao;

import static com.nts.reservationservice.dao.FileInfoDaoSqls.SELECT_FILEINFO_BY_ID;

import java.util.Collections;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservationservice.dto.FileInfoDto;

@Repository
public class FileInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<FileInfoDto> rowMapper = BeanPropertyRowMapper.newInstance(FileInfoDto.class);

	public FileInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public FileInfoDto selectFileInfoById(Long id) {
		Map<String, ?> map = Collections.singletonMap("id", id);
		return jdbc.queryForObject(SELECT_FILEINFO_BY_ID, map, rowMapper);
	}
}
